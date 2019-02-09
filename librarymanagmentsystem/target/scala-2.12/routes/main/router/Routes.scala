// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/hp/Downloads/w1673659/w1673659/Playframework/librarymanagmentsystem/conf/routes
// @DATE:Mon Dec 17 07:35:30 IST 2018

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  HomeController_0: controllers.HomeController,
  // @LINE:11
  Assets_1: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    HomeController_0: controllers.HomeController,
    // @LINE:11
    Assets_1: controllers.Assets
  ) = this(errorHandler, HomeController_0, Assets_1, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_0, Assets_1, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """readerlist""", """controllers.HomeController.readerList"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """generate""", """controllers.HomeController.generate"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """additem""", """controllers.HomeController.additem"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """return/""" + "$" + """isbn<[^/]+>""", """controllers.HomeController.returnItem(isbn:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """displayreservation""", """controllers.HomeController.displayReservations"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """addreader""", """controllers.HomeController.addReader"""),
    ("""POST""", this.prefix, """controllers.HomeController.login"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """delete/""" + "$" + """isbn<[^/]+>""", """controllers.HomeController.DeletItem(isbn:String)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """deletereader/""" + "$" + """id<[^/]+>""", """controllers.HomeController.deleteReader(id:String)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """deletereserv/""" + "$" + """id<[^/]+>""", """controllers.HomeController.deleteReservation(id:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """borrow""", """controllers.HomeController.borrow"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reserve""", """controllers.HomeController.reserve"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_0.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_HomeController_readerList1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("readerlist")))
  )
  private[this] lazy val controllers_HomeController_readerList1_invoker = createInvoker(
    HomeController_0.readerList,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "readerList",
      Nil,
      "GET",
      this.prefix + """readerlist""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val controllers_Assets_versioned2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned2_invoker = createInvoker(
    Assets_1.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_HomeController_generate3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("generate")))
  )
  private[this] lazy val controllers_HomeController_generate3_invoker = createInvoker(
    HomeController_0.generate,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "generate",
      Nil,
      "GET",
      this.prefix + """generate""",
      """""",
      Seq()
    )
  )

  // @LINE:15
  private[this] lazy val controllers_HomeController_additem4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("additem")))
  )
  private[this] lazy val controllers_HomeController_additem4_invoker = createInvoker(
    HomeController_0.additem,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "additem",
      Nil,
      "POST",
      this.prefix + """additem""",
      """""",
      Seq()
    )
  )

  // @LINE:18
  private[this] lazy val controllers_HomeController_returnItem5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("return/"), DynamicPart("isbn", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_returnItem5_invoker = createInvoker(
    HomeController_0.returnItem(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "returnItem",
      Seq(classOf[String]),
      "GET",
      this.prefix + """return/""" + "$" + """isbn<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:20
  private[this] lazy val controllers_HomeController_displayReservations6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("displayreservation")))
  )
  private[this] lazy val controllers_HomeController_displayReservations6_invoker = createInvoker(
    HomeController_0.displayReservations,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "displayReservations",
      Nil,
      "GET",
      this.prefix + """displayreservation""",
      """""",
      Seq()
    )
  )

  // @LINE:26
  private[this] lazy val controllers_HomeController_addReader7_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("addreader")))
  )
  private[this] lazy val controllers_HomeController_addReader7_invoker = createInvoker(
    HomeController_0.addReader,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "addReader",
      Nil,
      "POST",
      this.prefix + """addreader""",
      """""",
      Seq()
    )
  )

  // @LINE:28
  private[this] lazy val controllers_HomeController_login8_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_login8_invoker = createInvoker(
    HomeController_0.login,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "login",
      Nil,
      "POST",
      this.prefix + """""",
      """""",
      Seq()
    )
  )

  // @LINE:31
  private[this] lazy val controllers_HomeController_DeletItem9_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("delete/"), DynamicPart("isbn", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_DeletItem9_invoker = createInvoker(
    HomeController_0.DeletItem(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "DeletItem",
      Seq(classOf[String]),
      "DELETE",
      this.prefix + """delete/""" + "$" + """isbn<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:33
  private[this] lazy val controllers_HomeController_deleteReader10_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("deletereader/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_deleteReader10_invoker = createInvoker(
    HomeController_0.deleteReader(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "deleteReader",
      Seq(classOf[String]),
      "DELETE",
      this.prefix + """deletereader/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:35
  private[this] lazy val controllers_HomeController_deleteReservation11_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("deletereserv/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_deleteReservation11_invoker = createInvoker(
    HomeController_0.deleteReservation(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "deleteReservation",
      Seq(classOf[String]),
      "DELETE",
      this.prefix + """deletereserv/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:37
  private[this] lazy val controllers_HomeController_borrow12_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("borrow")))
  )
  private[this] lazy val controllers_HomeController_borrow12_invoker = createInvoker(
    HomeController_0.borrow,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "borrow",
      Nil,
      "POST",
      this.prefix + """borrow""",
      """""",
      Seq()
    )
  )

  // @LINE:39
  private[this] lazy val controllers_HomeController_reserve13_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reserve")))
  )
  private[this] lazy val controllers_HomeController_reserve13_invoker = createInvoker(
    HomeController_0.reserve,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "reserve",
      Nil,
      "POST",
      this.prefix + """reserve""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_0.index)
      }
  
    // @LINE:8
    case controllers_HomeController_readerList1_route(params@_) =>
      call { 
        controllers_HomeController_readerList1_invoker.call(HomeController_0.readerList)
      }
  
    // @LINE:11
    case controllers_Assets_versioned2_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned2_invoker.call(Assets_1.versioned(path, file))
      }
  
    // @LINE:13
    case controllers_HomeController_generate3_route(params@_) =>
      call { 
        controllers_HomeController_generate3_invoker.call(HomeController_0.generate)
      }
  
    // @LINE:15
    case controllers_HomeController_additem4_route(params@_) =>
      call { 
        controllers_HomeController_additem4_invoker.call(HomeController_0.additem)
      }
  
    // @LINE:18
    case controllers_HomeController_returnItem5_route(params@_) =>
      call(params.fromPath[String]("isbn", None)) { (isbn) =>
        controllers_HomeController_returnItem5_invoker.call(HomeController_0.returnItem(isbn))
      }
  
    // @LINE:20
    case controllers_HomeController_displayReservations6_route(params@_) =>
      call { 
        controllers_HomeController_displayReservations6_invoker.call(HomeController_0.displayReservations)
      }
  
    // @LINE:26
    case controllers_HomeController_addReader7_route(params@_) =>
      call { 
        controllers_HomeController_addReader7_invoker.call(HomeController_0.addReader)
      }
  
    // @LINE:28
    case controllers_HomeController_login8_route(params@_) =>
      call { 
        controllers_HomeController_login8_invoker.call(HomeController_0.login)
      }
  
    // @LINE:31
    case controllers_HomeController_DeletItem9_route(params@_) =>
      call(params.fromPath[String]("isbn", None)) { (isbn) =>
        controllers_HomeController_DeletItem9_invoker.call(HomeController_0.DeletItem(isbn))
      }
  
    // @LINE:33
    case controllers_HomeController_deleteReader10_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_HomeController_deleteReader10_invoker.call(HomeController_0.deleteReader(id))
      }
  
    // @LINE:35
    case controllers_HomeController_deleteReservation11_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_HomeController_deleteReservation11_invoker.call(HomeController_0.deleteReservation(id))
      }
  
    // @LINE:37
    case controllers_HomeController_borrow12_route(params@_) =>
      call { 
        controllers_HomeController_borrow12_invoker.call(HomeController_0.borrow)
      }
  
    // @LINE:39
    case controllers_HomeController_reserve13_route(params@_) =>
      call { 
        controllers_HomeController_reserve13_invoker.call(HomeController_0.reserve)
      }
  }
}
