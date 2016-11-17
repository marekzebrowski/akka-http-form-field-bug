import org.scalatest.{FlatSpec, Matchers}
import akka.http.scaladsl.model.{FormData, StatusCodes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
class FFRouteTest extends FlatSpec with Matchers with ScalatestRouteTest{
  val r= (new FFRoute).route
  "route" should "match formField" in {
    Post("/test", FormData(Map("myfield" ->"OK"))) ~> r ~> check {
      status should === (StatusCodes.OK)
      entityAs[String] should ===("passed OK")
    }
  }

}
