package fileIOComponent

trait FileIOInterface {

  def load(): String
  def save(gameState: String): String
}
