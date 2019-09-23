package models

import scala.collection.mutable

object TaskListInMemoryModel {

  private var tasks = mutable.Map[String, Seq[String]]("zvi" -> List("eat","sleep","repeat"))
  private var users = mutable.Map[String,String]("zvi" -> "pass")

  def validateUser(username: String, password: String): Boolean = {
    users.get(username).map( _ == password).getOrElse(false)
  }

  def createUser(username: String, password: String): Boolean = {
    if (users.contains(username)) false
    else
      users(username) = password
      true
  }

  def getTasks(username: String): Seq[String] = {
    tasks.get(username).getOrElse(Nil)
  }

  def addTask(username: String, task: String): Unit = ???

  def removeTask(username: String,idx: Int): Boolean = ???

}
