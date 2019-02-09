// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/hp/Downloads/w1673659/w1673659/Playframework/librarymanagmentsystem/conf/routes
// @DATE:Mon Dec 17 07:35:30 IST 2018

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:35
    def deleteReservation(id:String): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "deletereserv/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)))
    }
  
    // @LINE:31
    def DeletItem(isbn:String): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "delete/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("isbn", isbn)))
    }
  
    // @LINE:15
    def additem(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "additem")
    }
  
    // @LINE:13
    def generate(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "generate")
    }
  
    // @LINE:8
    def readerList(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "readerlist")
    }
  
    // @LINE:18
    def returnItem(isbn:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "return/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("isbn", isbn)))
    }
  
    // @LINE:37
    def borrow(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "borrow")
    }
  
    // @LINE:39
    def reserve(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "reserve")
    }
  
    // @LINE:33
    def deleteReader(id:String): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "deletereader/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)))
    }
  
    // @LINE:26
    def addReader(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "addreader")
    }
  
    // @LINE:20
    def displayReservations(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "displayreservation")
    }
  
    // @LINE:6
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
    // @LINE:28
    def login(): Call = {
      
      Call("POST", _prefix)
    }
  
  }

  // @LINE:11
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:11
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
