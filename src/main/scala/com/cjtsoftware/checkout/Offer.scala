package com.cjtsoftware.checkout


/**
  * Offers are represented a functions that take the whole item list and
  * return an Item representing the total amount to be take off the bill.
  *
  * I felt this was the simplest approach.
  * Other approaches considered were:
  * - Removing the free item from the item list
  * - Modifying the discounted item to have a price of 0
  * - Adding one discount item every time a discount is applied
  * - Working everything when we add up the total
  */
trait Offer {
  def discount(items: Seq[Item]) : Option[Item]
}


/**
  * Three for two is basically a more catchy sounding buy 3 get 1 free
  * so we can use the same method for both offers, just with different parameters
  */
class BuyXGetYFree(discountedItem: Item, x: Int, y:Int) extends Offer {

  override def discount(items: Seq[Item]): Option[Item] = {
    items.count(_.equals(discountedItem)) / x match {
      case 0 => None
      case count => {
        val discount = (discountedItem.price * y * count).bigDecimal.negate()
        Some( Item("discount", discount) )
      }
    }
  }
}
