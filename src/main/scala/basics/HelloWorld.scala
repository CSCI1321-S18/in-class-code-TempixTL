package basics

import io.StdIn._

/**
 * This is a basic main for you to start off with.
 */

//Eclipse commands:
//ctrl + shift + F = Format all code in file
//ctrl + F11 = Run

object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("Hello World!")
    println("What's your name?")
    val name = readLine()
    println("Your name is " + name)
  }

  def square(x: Double) = x * x

  def cube(x: Double) = x * x * x
}
