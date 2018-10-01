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
    val itemsWithDiscount = calculateDiscount(items)
    val total             = calculateTotal(itemsWithDiscount)

    total.setScale(2, RoundingMode.HALF_EVEN)
  }


  protected def calculateTotal(items: Seq[Item]) : BigDecimal =  {
    items.map(_.price).sum
  }


  /**
    * Processes each offer and adds the resulting discount to the Item list.
    * Discounts have a negative price value, so the calculateTotal method should
    * just factor those into the final bill
    *
    * E.g. [(apple, 0.60), (apple, 0.60)] becomes [(apple, 0.60), (apple, 0.60), (discount, -0.60)]
    */
  protected def calculateDiscount(items: Seq[Item]) : Seq[Item] = {
    items ++ offers.flatMap(_.discount(items))
  }

}

