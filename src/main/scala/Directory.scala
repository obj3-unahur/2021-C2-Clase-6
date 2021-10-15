package ar.edu.unahur

import scala.collection.mutable.Set

//class Directory(val files: Set[File])
class Directory[T <: File](val files: Set[T])

class Informer {
  def inform(directory: Directory[File]) = directory.files.foreach(_.showInfo())
}

class PreviewGenerator {
  def generatePreviews(directory: Directory[Video]) = directory.files.foreach(_.generatePreview())
}

object DirectoryApp extends App {
  val directory: Directory[File] = new Directory(Set(new File, new Image, new SlowMotionVideo))
  val directorioDeVideos: Directory[Video] = new Directory(Set(new Video, new SlowMotionVideo))
  directorioDeVideos.files.filter(video => video.length > 60)

  //  val wrongDirectory: Directory[Int] = new Directory(Set(1, 2, 3, 4))
}

object SetsApp extends App {
  //  val file: File = new Video

  //  var setVideos: Set[Video] = Set(new Video)
  //  var setFiles: Set[File] = setVideos

  //  var setFiles: Set[File] = Set(new File)
  //  var setVideos: Set[Video] = setFiles

  //
  //  setFiles.foreach(_.showInfo())
  //  setVideos.foreach(video => video.generatePreview())
  //
  //  setFiles.add(new Image)
  //  setVideos.foreach(_.generatePreview())

  var listVideos: List[Video] = List(new Video)
  var listFiles: List[File] = listVideos

  listFiles.foreach(_.showInfo())
  //  listFiles.add(new File)
  listVideos.foreach(_.generatePreview())
}

object FunctionApp extends App {
  var f: Function[Video, Video] = ???

  def g(video: Video): Video = ???

  def h(video: Video): File = ???
  def i(video: Video): SlowMotionVideo = ???

  def j(slowMotionVideo: SlowMotionVideo): Video = {
    if (slowMotionVideo.slowMotionLength > 0)
      slowMotionVideo.size += slowMotionVideo.slowMotionLength * 100

    new Video
  }
  def k(file: File): Video = {
    if (file.size > 1024)
      println(s"El archivo ${file.name} es pesado")
    else
      println(s"El archivo ${file.name} es liviano")

    new Video
  }

  //  f = g // OK

  //  f = h // NO
  //  f = i
  /* OK => Function[_, SlowMotionVideo] es SubTipo de Function[_, Video] y
           SlowMotionVideo es SubTipo de Video
           La salida es COVARIANTE: Function[_, +R]
  */

  //  f = j // NO
  //  f = k
  /* OK => Function[File, _] es SubTipo de Function[Video, _] y
            Video es SubTipo de File
            La entrada es CONTRAVARIANTE: Function[-T, _]
  */

  f(new Video).generatePreview()
}

object ContravarianzaApp extends App {
  abstract class Copier[-T <: File] {
    def copy(t: T): Unit
  }

  class FileCopier extends Copier[File] {
    override def copy(t: File): Unit = {
      println(s"Copying File of ${t.size} KB")
    }
  }

  class VideoCopier extends Copier[Video] {
    override def copy(t: Video): Unit = {
      println(s"Copying Video of ${t.length} minutes")
    }

    //    def m[A <: File](a: A): Unit = {
    //
    //    }
  }

  val vCopier: Copier[Video] = new VideoCopier
  vCopier.copy(new Video)

  val anotherVideoCopier: Copier[Video] = new FileCopier // Copier[File]
  // Quiero que Copier[File] sea SubTipo de Copier[Video]
  // Sabiendo que Video es SubTipo de File
  anotherVideoCopier.copy(new Video)


  //  val lalala: VideoCopier = new VideoCopier
  //  lalala.m[File](new Video)
}