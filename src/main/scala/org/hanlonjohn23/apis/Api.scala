package org.hanlonjohn23.apis

import io.circe.Decoder
import io.circe.parser.decode
import org.hanlonjohn23.http.HttpRequester

trait Api[T] {
  def httpRequester: HttpRequester

  def getAndDecode(params: Seq[(String, String)])(implicit decoder: Decoder[T]): T = {
    val responseBody = httpRequester.get(params)

    decode[T](responseBody) match {
      case Left(error) => throw error
      case Right(value) => value
    }
  }

  def fetchAndDecode(params: Seq[(String, String)])(implicit decoder: Decoder[T]): Seq[T] = {
    val responseBody = httpRequester.get(params)

    decode[Seq[T]](responseBody) match {
      case Left(error) => throw error
      case Right(value) => value
    }
  }
}
