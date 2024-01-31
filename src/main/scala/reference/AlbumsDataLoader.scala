package reference

import domain.AlbumSchema
import loader.AlbumLoader
import org.apache.spark.sql.{Dataset, SparkSession}

class AlbumsDataLoader extends AlbumLoader {
  override def load(spark: SparkSession): Dataset[AlbumSchema] = {
    import spark.implicits._
    val albumFilePath = "src/main/resources/albums.csv"
    spark.read.option("delimiter", ";").option("header", "true").csv(albumFilePath).as[AlbumSchema]
  }
}

object AlbumsDataLoader {
  def apply()(implicit spark: SparkSession): AlbumsDataLoader = new AlbumsDataLoader()
}




