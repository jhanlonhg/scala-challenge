package org.hanlonjohn23

trait HttpRequester {
  def get(params: Seq[(String, String)]): String
}
