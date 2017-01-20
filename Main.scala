import sys.process._

object Main {
  def main(args: Array[String]): Unit = {
    mavenPackage()
  }

  def mavenPackage(): Unit = {
    "mvn package" !
  }

  def createClassPath(classPath: String): String =
    s"org.ktz.example.hadoopexample" + classPath
}