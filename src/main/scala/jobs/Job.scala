package jobs

import domain.SalesReportSchema
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{DecimalType, DoubleType}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.{Dataset, SparkSession}
import loader.Extractor

class Job (spark: SparkSession, extractor: Extractor) {

  import spark.implicits._


  def run(): Unit = {
    (extract
      andThen transform
      andThen save
      )(())

  }

  def extract: Unit ⇒ InputSets = {

    (_: Unit) ⇒
      InputSets(
        extractor.albumLoader.load(spark),
        extractor.SalesLoader.load(spark),
        extractor.SongsLoader.load(spark)
      )
  }

  def transform(inputs: InputSets): Dataset[SalesReportSchema] = {

    val salesDF = inputs.salesEntries.toDF()
    val songsDF = inputs.songsEntries.toDF()
    val albumsDF = inputs.albumEntries.toDF()

    val mergedSalesSongsDF = salesDF.join(songsDF,
      col("TRACK_ISRC_CODE") === col("isrc") && col("TRACK_ID") === col("song_id"),
      "inner"
    )

    mergedSalesSongsDF.join(albumsDF,
      col("PRODUCT_UPC") === col("upc") && col("TERRITORY") === col("country"),
      "inner"
    )
      .drop("PRODUCT_UPC", "TRACK_ISRC_CODE", "TRACK_ID","DELIVERY","country" )
      .withColumnRenamed("NET_TOTAL", "total_net_revenue")
      .withColumnRenamed("TERRITORY", "sales_country")
      .withColumn("total_net_revenue", col("total_net_revenue").cast(DoubleType))
      .withColumn("song_id", col("song_id").cast(DecimalType(38, 0)))
      .select(
        "upc",
        "isrc",
        "label_name",
        "album_name",
        "song_id",
        "song_name",
        "artist_name",
        "content_type",
        "total_net_revenue",
        "sales_country"
      )
      .as[SalesReportSchema]
  }

  def readCSV(filePath: String): DataFrame = {
    spark.read.option("delimiter", ";").option("header", "true").csv(filePath)
  }

  def save(salesReport: Dataset[SalesReportSchema]): Unit = {
    salesReport.write.format("delta").save("/Users/akara/Desktop/BelieveTest/src/output")
  }
}


