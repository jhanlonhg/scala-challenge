package org.hanlonjohn23

import scalaj.http.Http

class ScalaJHttpRequester(url: String) extends HttpRequester {
  override def get(params: Map[String, String]): String = {
    val httpRequest = Http(url)

    // Add params to Http
    for ((param, value) <- params) httpRequest.param(param, value)

    httpRequest
      .asString   // Make request
      .throwError // Throw error if applicable
      .body       // Return body as String
  }
}
