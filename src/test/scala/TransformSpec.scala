import Fixtures._
import jobs.{InputSets, Job}
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import utils.JobSpec

class TransformSpec extends JobSpec {

  import sparkSession.implicits._

  val job = new Job(sparkSession,extractor)

  "transform" should "merge inputs & produce Dataset[SalesReportSchema]" in {

    val expected = sparkSession.createDataset(expectedSalesReport)
    val albumDS = sparkSession.createDataset(albumEntries)
    val salesDS = sparkSession.createDataset(salesEntries)
    val songsDS = sparkSession.createDataset(songsEntries)
    val result = job.transform(InputSets(albumDS, salesDS, songsDS))

    result.collect() shouldEqual expected.collect()

  }
}
