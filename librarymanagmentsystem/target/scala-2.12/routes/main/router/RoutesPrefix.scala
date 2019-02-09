// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/hp/Downloads/w1673659/w1673659/Playframework/librarymanagmentsystem/conf/routes
// @DATE:Mon Dec 17 07:35:30 IST 2018


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
