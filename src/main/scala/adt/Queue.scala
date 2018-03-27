package adt

trait Queue[A] {
  
  def enqueue(element: A): Unit
  def dequeue(): A
  def peek: A
  def isEmpty: Boolean
  
}