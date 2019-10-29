package com.oxymorus.greeting

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class LoadScript extends Simulation {

  val baseUrl = "http://localhost:8080"

  val dataFile = "data.csv"

  val dataFeeder = ssv(dataFile).circular

  val httpConfig = http
    .baseUrl(baseUrl)
    .acceptHeader("application/stream+json")

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
        .check(status is 200,
          jsonPath("$.id").saveAs("id")
        )
    )
    .exec(
      http("Get greeting by id")
        .get("/greetings")
        .queryParam("user", "${id}")
        .check(status is 200)
    )
}
