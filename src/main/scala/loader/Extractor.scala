package loader

import org.apache.spark.sql.SparkSession
import reference.{AlbumsDataLoader, SalesDataLoader, SongsDataLoader}

case class Extractor(
                      albumLoader: AlbumsDataLoader,
                      SalesLoader: SalesDataLoader,
                      SongsLoader: SongsDataLoader)

object Extractor {
  def toExtractor(implicit sparkSession: SparkSession): Extractor = {

    Extractor(
      AlbumsDataLoader.apply(),
      SalesDataLoader.apply(),
      SongsDataLoader.apply()
    )
  }
}