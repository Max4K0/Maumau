package fileIOComponent

import play.api.libs.json.JsValue

trait fileIO_Interface {

  def load(): String
  def save(s: String): Unit
}
