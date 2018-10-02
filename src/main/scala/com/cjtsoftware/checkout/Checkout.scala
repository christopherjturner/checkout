package com.cjtsoftware.checkout

import scala.math.BigDecimal.RoundingMode


object Checkout {

  // Going forward I'd create Checkout as a class and pass the pricelist and offers
  // in as parameters, or pass them into the function itself.
  val apple  = Item("apple", BigDecimal(0.60))
  val orange = Item("orange", BigDecimal(0.25))

  val priceList : Map[String, Item] = Map(
    "apple"  -> apple,
    "orange" -> orange
  )

  val offerApples  = new BuyXGetYFree(apple, 2, 1)
  val offerOranges = new BuyXGetYFree(orange, 3, 1)

  val offers :Seq[Offer] = Seq(offerApples, offerOranges)


  /**
    * Assumptions made:
    * - Input will be a list of strings containing the item name
    * - Unknown items will be silently dropped
    * - Return value will be BigDecimal, rounded to 2 decimal places
    */
  def apply(itemNames: Seq[String]) : BigDecimal = {

    val items             = itemNames.flatMap(priceList.get)
    val discounts         = offers.flatMap(_.discount(items))
    val total             = calculateTotal(items ++ discounts)

    total.setScale(2, RoundingMode.HALF_EVEN)
  }


  protected def calculateTotal(items: Seq[Item]) : BigDecimal =  {
    items.map(_.price).sum
  }


}

