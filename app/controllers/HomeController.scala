package controllers

import javax.inject._
import play.api._
import play.api.mvc._
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  // Index Method for './'
  def index() = Action {
    Ok(views.html.index()) // Redirect './' to views/index
  }


}
