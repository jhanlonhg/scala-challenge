package org.hanlonjohn23

case class Address(
                    street: String,
                    city: String,
                    state: String
                  ) {
  def getStreetAddress: String = {
    s"$street $city, $state"
  }
}