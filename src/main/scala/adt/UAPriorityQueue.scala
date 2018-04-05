package adt

import scala.reflect.ClassTag

//Unsorted Array Priority Queue
class UAPriorityQueue[A: ClassTag](higherP: (A, A) => Boolean) extends MyPriorityQueue[A] {
  
  private var arr = new Array[A](10)
  private var end = 0
  
  def enqueue(a: A): Unit = {
    if (end == arr.length) {
      val newArr = new Array[A](arr.length * 2)
      Array.copy(arr, 0, newArr, 0, arr.length)
      arr = newArr
    }
    arr(end) = a
    end += 1
  }
  
  def dequeue(): A = {
    val retIndex = getHighestPriority()
    val ret = arr(retIndex)
    
    arr(retIndex) = arr(end-1)
    end -= 1
    
    ret
  }
  
  def peek: A = {
    arr(getHighestPriority())
  }
  
  def isEmpty: Boolean = {
    end == 0
  }
  
  private def getHighestPriority(): Int = {
    var index = 0
    for (x <- 0 until end; if higherP(arr(x), arr(index)))
      index = x
    
    index
  }
  
}