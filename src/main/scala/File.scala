package ar.edu.unahur

class File {
  var name: String = ""
  var size: Int = 0

  def showInfo() = println(s"${name} [${size} KB]")
}

class Image extends File {
  var megaPixels: Int = 0

  //  override def showInfo() = throw new UnsupportedOperationException
}

class Video extends File {
  var length: Int = 0

  def generatePreview() = ???
}

class SlowMotionVideo extends Video {
  var slowMotionLength: Int = 0
}

object Application extends App {
  val file: File = new Image
  file.showInfo()

  val video: Video = new SlowMotionVideo
  //  video.slowMotionLength

  var unSet: Set[File] = Set(new Image, new Image)
  unSet.filter(file => file.size > 1024)
}