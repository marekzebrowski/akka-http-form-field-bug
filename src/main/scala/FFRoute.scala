import akka.http.scaladsl.model.ContentTypes
import akka.http.scaladsl.server._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.unmarshalling.Unmarshaller.stringUnmarshaller
import akka.http.scaladsl.unmarshalling.{FromEntityUnmarshaller}

class FFRoute {
  /*
  removing unmarshaller makes test pass, implicit resolution kicks in correctly
   */
  implicit def unmarshaller[T]: FromEntityUnmarshaller[T] =
    stringUnmarshaller.forContentTypes(
      ContentTypes.`application/json`
    ).map(s => throw new RuntimeException(s"fail to unmarshal ${s}"))

  def route: Route = (post & path("test") & formField("myfield")) { mf => println(s"MF ${mf}"); complete(s"passed ${mf}")}
}
