package jobs

import domain.{AlbumSchema, SaleSchema, SongSchema}
import org.apache.spark.sql.Dataset

case class InputSets(
                      albumEntries: Dataset[AlbumSchema],
                      salesEntries: Dataset[SaleSchema],
                      songsEntries: Dataset[SongSchema])