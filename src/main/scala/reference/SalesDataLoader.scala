package reference

import domain.{AlbumSchema, SaleSchema}
import loader.{AlbumLoader, SalesLoader}
import org.apache.spark.sql.{Dataset, SparkSession}

class SalesDataLoader extends SalesLoader {
  override def load(spark: SparkSession): Dataset[SaleSchema] = {
    import spark.implicits._
    val saleFilePath = "src/main/resources/sales.csv"

    spark.read.option("delimiter", ";").option("header", "true").csv(saleFilePath).as[SaleSchema]
  }
}

object SalesDataLoader {
  def apply()(implicit spark: SparkSession): SalesDataLoader = new SalesDataLoader()
}