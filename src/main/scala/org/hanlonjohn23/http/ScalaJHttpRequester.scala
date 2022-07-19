package org.hanlonjohn23.http

import scalaj.http.Http

class ScalaJHttpRequester(url: String) extends HttpRequester {
  override def get(params: Seq[(String, String)]): String = {
    Http(url)
      .params(params)
      .asString // Make request
      .throwError // Throw error if applicable
      .body // Return body as String
  }
}
