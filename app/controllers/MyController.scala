package controllers
import javax.inject.{Inject, Singleton}
import models.TaskListInMemoryModel
import play.api.mvc.{AbstractController, ControllerComponents}

@Singleton
class MyController @Inject()(cc: ControllerComponents) extends AbstractController(cc)  {

  /* This Method is responsible to show the TasksList for the current user */
  def TasksList = Action { request =>
    val usernameOption: Option[String] = request.session.get("username")
    usernameOption.map { username =>
        val tasks : Seq[String] = TaskListInMemoryModel.getTasks(username)
        Ok(views.html.MyMainView(tasks))
    }.getOrElse(Redirect(routes.HomeController.index()))
  } // Must Return Result!


  def CreateUser = Action {
    request =>
      // username = List(MyUserNameInput), password = List(MyPasswordInput)
      val values: Option[Map[String,Seq[String]]] = request.body.asFormUrlEncoded
      values.map { value =>
        val username = value("username").head
        val password = value("password").head
        if(TaskListInMemoryModel.createUser(username,password))
          Redirect(routes.MyController.TasksList()).withSession("username" -> username)
        else
          Redirect(routes.HomeController.index())
      }.getOrElse(Redirect(routes.HomeController.index()))
  }

  /* This method is responsible for the GET form */
  def validateLoginGET(username: String, password:String) = Action {
    Ok(s"welcome $username with password $password")
  }

  /* This method is responsible to the POST form */
  def validateLogingPOST = Action {
    request =>
      // username = List(MyUserNameInput), password = List(MyPasswordInput)
      val values: Option[Map[String,Seq[String]]] = request.body.asFormUrlEncoded
      values.map { value =>
        val username = value("username").head
        val password = value("password").head
        if(TaskListInMemoryModel.validateUser(username,password))
          Redirect(routes.MyController.TasksList()).withSession("username" -> username)
        else
          Redirect(routes.HomeController.index())
      }.getOrElse(Redirect(routes.HomeController.index()))
  }

  def logout = Action {
    Redirect(routes.HomeController.index()).withNewSession
  }
}
