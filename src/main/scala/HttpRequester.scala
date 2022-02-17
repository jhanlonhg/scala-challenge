package org.hanlonjohn23

trait HttpRequester {
  def get(params: Map[String, String]): String
}
