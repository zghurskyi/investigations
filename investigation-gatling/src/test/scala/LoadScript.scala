import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class LoadScript extends Simulation {

  val config = ConfigFactory.load()
  val baseUrl = config.getString("baseUrl")
  val dataFile = config.getString("dataFile")

  val dataFeeder = ssv(dataFile).circular

  val httpConfig = http
    .baseUrl(baseUrl)
    .contentTypeHeader("application/json")
    .acceptHeader("application/json")
    .shareConnections

  val basicLoad = scenario("LOAD_TEST")
    .feed(dataFeeder)
    .exec(BasicLoad.start)

  setUp(
    basicLoad.inject(
      rampConcurrentUsers(0) to (400) during (10 seconds),
      constantConcurrentUsers(400) during (50 seconds)
    ).protocols(httpConfig)
  )

}

object BasicLoad {

  val start =
    exec(
      http("Register greeting")
        .post("/greetings")
        .body(StringBody(
          """
            |{
            |  "name": "${name}",
            |  "greeting": "${greeting}"
            |}
            |""".stripMargin)).asJson
        .check(status is 200)
        .check(jsonPath("$.id").saveAs("id"))
    )
    .exec(
      http("Get greeting by id")
        .get("/greetings")
        .queryParam("id", "${id}")
        .check(status is 200)
    )
}
