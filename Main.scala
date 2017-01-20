import sys.process._

object Main {
  def main(args: Array[String]): Unit = {
    mavenPackage() !
    runHadoopCompression(args.head, args.tail.toList)
  }

  def mavenPackage(): String = {
    "mvn package"
  }

  def runHadoop(classPath: String, args: List[String]): String = {
    s"hadoop jar target/HadoopExample-0.1-SNAPSHOT.jar ${createClassPath(classPath)} " +
      s"${args: _*}"
  }

  def runHadoopCompression(classPath: String, args: List[String]): Unit = {
    s"""echo "Text"|${runHadoop(classPath, args)}\ | gunzip - """ !
  }

  def createClassPath(classPath: String): String =
    s"org.ktz.example.hadoopexample" + classPath
}