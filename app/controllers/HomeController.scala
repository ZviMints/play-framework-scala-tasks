package controllers

import javax.inject._
import play.api._
import play.api.mvc._
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  // Index Method for './'
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.MyMainView()) // Redirect './' to views/MyMainView
  }
}
