package loader

import domain.{AlbumSchema, SaleSchema, SongSchema}
import org.apache.spark.sql.{Dataset, SparkSession}

trait Loader[T] {
  def load(spark: SparkSession): Dataset[T]
}

trait AlbumLoader extends Loader[AlbumSchema]
trait SalesLoader extends Loader[SaleSchema]
trait SongLoader extends Loader[SongSchema]