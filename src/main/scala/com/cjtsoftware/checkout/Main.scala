package com.cjtsoftware.checkout

object Main extends App {

  val items = Seq("apple", "apple", "apple", "orange", "orange")
  val total = Checkout(items)

  items.foreach(println)
  println("------------")
  println(s"Total: £$total")

}
