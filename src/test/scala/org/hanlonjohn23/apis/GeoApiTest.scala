package org.hanlonjohn23.apis

// TODO: Still not sure how I should be

//class MockHttpRequester(url: String) extends HttpRequester {
//  override def get(params: Seq[(String, String)]): String = {
//    val byCityParam = params.find(param => param._1 == "by_city").getOrElse(("by_city", "Wrong City"))
//    s"""
//       |[{
//       |	"name": "The Foo Bar",
//       |	"street": "1337 Baz Ave",
//       |	"city": "${byCityParam._2}",
//       |	"state": "California",
//       |	"country": "United States",
//       |	"longitude": null,
//       |	"latitude": null
//       |}]""".stripMargin
//  }
//}
//
//class GeoApiTest extends AnyFunSuite {
//  // Initialize GeoApi for testing
//  val geoApi = new GeoApi(new MockHttpRequester("http://notarealurl.com"))
//
//  // Fixtures
//  val address = Address(street = ???, city = ???, state = ???)
//
//  test()
//
//}