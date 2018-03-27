package adt

trait MySeq[A] {
  def apply(index: Int): A
  def update(index: Int, value: A): Unit
  def add(index: Int, value: A): Unit
  def remove(index: Int): A
  def length: Int
  def size = length
}