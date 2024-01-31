package reference

import domain.{SongSchema}
import loader.{SalesLoader, SongLoader}
import org.apache.spark.sql.{Dataset, SparkSession}

class SongsDataLoader extends SongLoader {
  override def load(spark: SparkSession): Dataset[SongSchema] = {
    import spark.implicits._
    val songFilePath = "src/main/resources/songs.csv"
    spark.read.option("delimiter", ";").option("header", "true").csv(songFilePath).as[SongSchema]
  }
}

object SongsDataLoader {
  def apply()(implicit spark: SparkSession): SongsDataLoader = new SongsDataLoader()
}