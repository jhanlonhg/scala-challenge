package org.hanlonjohn23.http

trait HttpRequester {
  def get(params: Seq[(String, String)]): String
}
