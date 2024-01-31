package utils

import loader.Extractor
import org.apache.hadoop.conf.{ Configuration => HadoopConfiguration }
import org.apache.spark.sql.{Dataset, Encoder, SparkSession}
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

trait JobSpec extends SupportSpec {

  implicit val sparkSession: SparkSession = getOrCreateSparkSession()
  val hadoopConf: HadoopConfiguration = sparkSession.sparkContext.hadoopConfiguration

  val extractor: Extractor = Extractor(null, null, null)

  protected def assertResultIsEmpty[T <: Product](result: Dataset[T]): Unit = {
    result shouldBe empty
  }

  def no[T: Encoder]: Dataset[T] = sparkSession.emptyDataset[T]

}
