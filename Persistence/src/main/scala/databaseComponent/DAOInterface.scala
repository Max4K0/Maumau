package databaseComponent

trait DAOInterface {
  def save(): Unit
  def read:String
  def update(input: String): Unit
  def delete(): Unit
}
