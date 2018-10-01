package com.cjtsoftware.checkout

import scala.math.BigDecimal.RoundingMode

object Checkout {

  /**
    * Given the shop sells only apples and oranges, price list can be hardcoded
    */
  val prices : Map[String, BigDecimal] = Map(
    "apple"  -> BigDecimal(0.60),
    "orange" -> BigDecimal(0.25))


  /**
    * Assumptions made:
    * - Input will be a list of strings containing the item name
    * - Unknown items will be silently dropped
    * - Return value will be BigDecimal, rounded to 2 decimal places
    */
  def apply(items: Seq[String]) : BigDecimal = {
    items
      .flatMap(prices.get)
      .sum
      .setScale(2, RoundingMode.HALF_EVEN)
  }

}
