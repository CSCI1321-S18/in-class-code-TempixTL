package recursion

import io.StdIn._

object Fibonacci extends App {
  
  def fibonacci(n: Int): Int = {
    if (n <= 2) 1
    else fibonacci(n-2) + fibonacci(n-1)
  }
  
  val num = readLine.toInt
  println(fibonacci(num))
  
}