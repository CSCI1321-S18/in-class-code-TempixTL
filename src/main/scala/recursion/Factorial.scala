package recursion

import io.StdIn._

object Factorial extends App {
  
  def factorial(n: Int): Int = {
    if (n <= 1) n
    else n * factorial(n-1)
  }
  
  val num = readLine.toInt
  println(factorial(num))
  
}