package org.hanlonjohn23

object AddressDefaults {
  val SUITE_REGEX = " (Ste|Apt|Unit) [a-zA-Z0-9]{1,4}"
}

case class Address(
                    street: String,
                    city: String,
                    state: String
                  ) {
  def getStreetAddress: String = {
    s"${removeSubAddress(street)} $city, $state"
  }

  private def removeSubAddress(street: String): String = {
    // Remove sub-address information (Ste, Apt, or Unit) as it causes issues with the Geocoder service
    street.replaceAll(AddressDefaults.SUITE_REGEX, "")
  }
}