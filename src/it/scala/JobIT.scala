import org.apache.spark.sql.{DataFrame, SparkSession}
import org.scalatest.BeforeAndAfterAll
import loader.Extractor
import jobs.Job
import org.scalatest.flatspec.{AnyFlatSpec}


class JobIT extends AnyFlatSpec with  BeforeAndAfterAll  {

  // Initialize SparkSession for testing
  val spark: SparkSession = SparkSession.builder
    .appName("JobIntegrationTest")
    .master("local[2]")
    .getOrCreate()

  // Your Job instance
  val processor = new Job(
    spark,
    Extractor.toExtractor(spark)
  )

  override def beforeAll(): Unit = {
    super.beforeAll()
  }

  override def afterAll(): Unit = {
    super.afterAll()
    spark.stop()
  }

  "Job" should "run successfully" in {
    processor.run()
    val resultData: DataFrame = spark.read.format("delta").load("/Users/akara/Desktop/BelieveTest/src/output")

    assert(resultData.count() > 0)
  }

}
