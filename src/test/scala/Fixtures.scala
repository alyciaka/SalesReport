import domain.{AlbumSchema, SaleSchema, SalesReportSchema, SongSchema}

object Fixtures {

  val albumEntries: List[AlbumSchema] = List(
    AlbumSchema("5016958061173", "Chillin with Santa", "Music of Life", "AUS")
  )
  val salesEntries: List[SaleSchema] = List(
    SaleSchema("5016958061173", "GBJUT1300925", "1229417", "download","0.60","AUS")
  )

  val songsEntries: List[SongSchema] = List(
    SongSchema("GBJUT1300925", "1229417", "Chillin with Santa", "Derek B","single")
  )

  val expectedSalesReport: List[SalesReportSchema] = List(
    SalesReportSchema("5016958061173","GBJUT1300925","Music of Life","Chillin with Santa",1229417,"Chillin with Santa","Derek B","single",0.6,"AUS")
  )

}
