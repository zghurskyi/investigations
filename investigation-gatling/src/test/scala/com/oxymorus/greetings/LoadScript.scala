package com.oxymorus.greetings

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class LoadScript extends Simulation {

  val baseUrl = "http://localhost:8080"

  val filePath = "C:\\data.csv"

  val httpConf = http
    .baseUrl(baseUrl)
    .acceptHeader("application/stream+json")

  val basicLoad = scenario("LOAD_TEST")
    .feed(csv(filePath).circular)
    .exec(BasicLoad.start)

  setUp(
    basicLoad.inject(
      rampConcurrentUsers(0) to (400) during (10 seconds),
      constantConcurrentUsers(400) during (50 seconds)
    ).protocols(httpConf)
  ).maxDuration(2 minutes)

}

object BasicLoad {

  val start =
    exec(
      http("Register greeting")
        .post("/greetings")
        .body(StringBody(
          """
            |{
            |  "name": "${name}"
            |}
            |""".stripMargin)).asJson
        .check(status is 200)
    )
    .exec(
      http("Get greeting by user")
        .get("/greetings")
        .queryParam("user", "${user}")
        .check(status is 200)
    )
}