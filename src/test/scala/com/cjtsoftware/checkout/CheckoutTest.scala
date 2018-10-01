package com.cjtsoftware.checkout

import org.scalatest.FlatSpec

class CheckoutTest extends FlatSpec {

  "An empty item list" should "calculate a total of 0.00" in {
    assert( Checkout(Seq()) == BigDecimal(0.00) )
  }

  "One apple" should "cost 0.60" in {
    assert( Checkout(Seq("apple")) == BigDecimal(0.60) )
  }

  "Three apples" should "cost 1.80" in {
    assert( Checkout(Seq("apple", "apple", "apple"))  == BigDecimal(1.80) )
  }


  "An Orange" should "cost 0.25" in {
    assert( Checkout(Seq("orange")) == BigDecimal(0.25))
  }


  "Two Oranges" should "cost 0.50" in {
    assert( Checkout(Seq("orange", "orange")) == BigDecimal(0.50))
  }

  "Two oranges and two apples" should "cost 1.70" in {
    assert( Checkout(Seq("apple", "orange", "apple", "orange")) == BigDecimal(1.70))
  }


  "Items not in the price list" should "be silently dropped" in {
    assert( Checkout(Seq("cake", "newspaper", "pineapple")) == BigDecimal(0.00) )
  }



}
