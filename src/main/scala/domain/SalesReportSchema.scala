package domain

case class SalesReportSchema(upc: String,
                             isrc: String,
                             label_name: String,
                             album_name: String,
                             song_id: BigInt,
                             song_name: String,
                             artist_name: String,
                             content_type: String,
                             total_net_revenue: Double,
                             sales_country: String
                            )

object SalesReportSchema {

  object Fields {
    val upc = "upc"
    val isrc = "isrc"
    val label_name = "label_name"
    val album_name = "album_name"
    val song_id = "song_id"
    val song_name = "song_name"
    val artist_name = "artist_name"
    val content_type = "content_type"
    val total_net_revenue = "total_net_revenue"
    val sales_country = "sales_country"
  }
}