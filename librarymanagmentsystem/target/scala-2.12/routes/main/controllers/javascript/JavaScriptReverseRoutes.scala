// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/hp/Downloads/w1673659/w1673659/Playframework/librarymanagmentsystem/conf/routes
// @DATE:Mon Dec 17 07:35:30 IST 2018

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers.javascript {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:35
    def deleteReservation: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.deleteReservation",
      """
        function(id0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "deletereserv/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:31
    def DeletItem: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.DeletItem",
      """
        function(isbn0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "delete/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("isbn", isbn0))})
        }
      """
    )
  
    // @LINE:15
    def additem: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.additem",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "additem"})
        }
      """
    )
  
    // @LINE:13
    def generate: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.generate",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "generate"})
        }
      """
    )
  
    // @LINE:8
    def readerList: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.readerList",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "readerlist"})
        }
      """
    )
  
    // @LINE:18
    def returnItem: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.returnItem",
      """
        function(isbn0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "return/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("isbn", isbn0))})
        }
      """
    )
  
    // @LINE:37
    def borrow: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.borrow",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "borrow"})
        }
      """
    )
  
    // @LINE:39
    def reserve: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.reserve",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "reserve"})
        }
      """
    )
  
    // @LINE:33
    def deleteReader: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.deleteReader",
      """
        function(id0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "deletereader/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:26
    def addReader: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.addReader",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addreader"})
        }
      """
    )
  
    // @LINE:20
    def displayReservations: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.displayReservations",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "displayreservation"})
        }
      """
    )
  
    // @LINE:6
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
    // @LINE:28
    def login: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.login",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + """"})
        }
      """
    )
  
  }

  // @LINE:11
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:11
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }


}
