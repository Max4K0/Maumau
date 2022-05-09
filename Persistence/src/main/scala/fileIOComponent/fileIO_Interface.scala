package fileIOComponent

trait fileIO_Interface {

  def load(): String
  def save(gameState: String): Unit
}
