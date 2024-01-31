package utils

import org.apache.spark.serializer.KryoSerializer
import org.apache.spark.sql.{Dataset, Encoder, SparkSession}
import org.scalatest.flatspec.AnyFlatSpecLike
import org.scalatest.matchers.must.Matchers
import org.scalatest.{BeforeAndAfterAll, GivenWhenThen}
import org.slf4j.{Logger, LoggerFactory}
import scala.reflect.runtime.universe._

trait SupportSpec extends AnyFlatSpecLike
  with Matchers
  with BeforeAndAfterAll
  with GivenWhenThen {

  val logger: Logger = LoggerFactory.getLogger(getClass)

  def getOrCreateSparkSession(): SparkSession = {
    val executors = 1
    val sparkSession =
      SparkSession
        .builder()
        .master(s"local[$executors]")
        .config("spark.ui.enabled", false)
        .config("spark.sql.shuffle.partitions", executors)
        .config("spark.serializer", classOf[KryoSerializer].getName)
        .config("spark.driver.allowMultipleContexts", "true")
        .config("spark.sql.codegen.wholeStage", "false")
        .appName(getClass.getName)
        .getOrCreate()
    Runtime.getRuntime.addShutdownHook(new Thread() {
      override def run(): Unit = sparkSession.close()
    })
    sparkSession
  }

  implicit class SparkSessionExt(sparkSession: SparkSession) {
    def createDataset[T: Encoder](data: T*): Dataset[T] = {
      sparkSession.createDataset(data)
    }
  }

}
