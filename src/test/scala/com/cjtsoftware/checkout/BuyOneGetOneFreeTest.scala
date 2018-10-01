package com.cjtsoftware.checkout

import org.scalatest.FlatSpec

class BuyOneGetOneFreeTest extends FlatSpec {

  val apple = Item("apple", BigDecimal(0.60))
  val orange = Item("orange", BigDecimal(0.25))

  val byogofApples = new BuyXGetYFree(apple, 2, 1)

  //new BuyOneGetOneFree(apple)

  "Buy one get one free" should "give no discount on 0 apples" in {
    assert( byogofApples.discount(Seq()).isEmpty )
  }


  it should "give no discount on 1 apple" in {
    assert( byogofApples.discount(Seq()).isEmpty )
  }


  it should "give a -0.60 discount on two apples" in {
    assert( byogofApples.discount(Seq(apple, apple)).contains(Item("discount", BigDecimal(-0.60))) )
  }


  it should "give a -0.60 discount on three apples" in {
    assert( byogofApples.discount(Seq(apple, apple, apple)).contains(Item("discount", BigDecimal(-0.60))) )
  }


  it should "give a -1.20 discount on four apples" in {
    assert( byogofApples.discount(Seq(apple, apple, apple,apple)).contains(Item("discount", BigDecimal(-1.20))) )
  }


  it should "give no discount on one apple and one orange" in {
    assert( byogofApples.discount(Seq(apple, orange)).isEmpty)
  }

}
