package com.cjtsoftware.checkout

import org.scalatest.FlatSpec

class ThreeForTwoTest extends FlatSpec {


  val apple = Item("apple", BigDecimal(0.60))
  val orange = Item("orange", BigDecimal(0.25))

  val offer = new BuyXGetYFree(orange, 3, 1)
    //ThreeForTwo(orange)


  "Three for two on oranges" should "give no discount for an empty list" in {
    assert( offer.discount(Seq()).isEmpty )
  }


  it should "give no discount on one orange" in {
    assert( offer.discount(Seq(orange)).isEmpty )
  }


  it should "give no discount on two oranges" in {
    assert( offer.discount(Seq(orange, orange)).isEmpty )
  }


  it should "give -0.25 discount on three oranges" in {
    assert( offer.discount(Seq(orange, orange, orange)).contains(Item("discount", BigDecimal(-0.25))) )
  }


  it should "give -0.25 discount on four oranges" in {
    assert( offer.discount(Seq(orange, orange, orange, orange)).contains(Item("discount", BigDecimal(-0.25))) )
  }


  it should "give -0.25 discount on five  oranges" in {
    assert( offer.discount(Seq(orange, orange, orange, orange, orange)).contains(Item("discount", BigDecimal(-0.25))) )
  }


  it should "give -0.50 discount on six  oranges" in {
    assert( offer.discount(Seq(orange, orange, orange, orange, orange, orange)).contains(Item("discount", BigDecimal(-0.50))) )
  }

}
