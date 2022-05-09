/*package fileIOComponent.fileIO_XmlImpl

import fileIOComponent.fileIO_Interface

import java.io.*
import scala.xml.{Elem, PrettyPrinter}

class FileIO extends fileIO_Interface{



  override def load(controller: ControllerInterface): Unit = {
    val file = scala.xml.XML.loadFile("save.xml")
    val f1 = file \\ "theme"
    val cardTheme = (f1 \ "visableCardThemeManager").text
    val mainTheme = (f1 \ "visableMainThemeManager").text
    controller.changeCardTheme(cardTheme(0).asDigit)
    controller.changeMainTheme(mainTheme(0).asDigit)
  }

  def saveGame(controller: ControllerInterface): Elem = {
    <game>
      {theme_toXML(controller)}
    </game>
  }

  def theme_toXML(controller: ControllerInterface): Elem = {
    <theme>
      <visableCardThemeManager>{controller.visiblecardthememanager}</visableCardThemeManager>
      <visableMainThemeManager>{controller.visiblethememanager}</visableMainThemeManager>
    </theme>
  }

  override def save(controller: ControllerInterface): Unit = {

    val pw = new PrintWriter(new File("save.xml"))
    val prettyPrinter = new PrettyPrinter(120, 6)
    val xml = prettyPrinter.format(saveGame(controller))
    pw.write(xml)
    pw.close()
  }
}
*/