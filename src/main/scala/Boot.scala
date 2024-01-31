import org.apache.spark.sql.SparkSession
import jobs.Job
import loader.Extractor
import org.apache.hadoop.conf.Configuration

object Boot extends App {

  def hadoopConf: Configuration = spark.sparkContext.hadoopConfiguration

  implicit val spark: SparkSession = SparkSession
    .builder()
    .config("spark.sql.shuffle.partitions", "300")
    .appName("BelieveTest")
    .master("local[*]")
    .getOrCreate()

  new Job(spark,Extractor.toExtractor(spark)).run()
  spark.close()
}
