package adt

import collection.mutable

class BSTMap[K, V](startValues: (K, V)*)(comp: (K, K) => Int) extends mutable.Map[K, V] {
  import BSTMap._
  private var root: Node[K, V] = null
  startValues.foreach(this += _)
  
  def get(key: K): Option[V] = {
    var rover = root
    while (rover != null && comp(key, rover.key) != 0) {
      rover = if (comp(key, rover.key) < 0) rover.lhs else rover.rhs
    }
    
    if (rover == null) None
    else Some(rover.value)
  }
  
  def iterator: Iterator[(K, V)] = new Iterator[(K, V)] {
    import collection.mutable.ArrayStack
    val stack = new ArrayStack[Node[K, V]]()
    pushAllLeft(root)
    
    def hasNext = stack.nonEmpty
    def next: (K, V) = {
      val ret = stack.pop()
      pushAllLeft(ret.rhs)
      ret.key -> ret.value
    }
    
    private def pushAllLeft(n: Node[K, V]): Unit = if (n != null) {
      stack.push(n)
      pushAllLeft(n.lhs)
    }
  }
  
  def +=(kv: (K, V)) = {
    val (key, value) = kv
    def recur(n: Node[K, V]): Node[K, V] = {
      if (n == null) new Node(null, key, value, null) 
      else {
        val c = comp(key, n.key)
        
        if (c == 0) n.value = value
        else if (c < 0) n.lhs = recur(n.lhs)
        else n.rhs = recur(n.rhs)
        
        n
      }
    }
    root = recur(root)
    this
  }
  
  def -=(key: K) = {
    def recur(n: Node[K, V]): Node[K, V] = {
      if (n == null) null
      else {
        val c = comp(key, n.key)
        
        if (c == 0) {
          if (n.lhs == null) n.rhs
          else if (n.rhs == null) n.lhs
          else {
            val (k, v, node) = removeMax(n.lhs)
            n.lhs = node
            n.key = k
            n.value = v
            n
          }
        }
        else if (c < 0) {
          n.lhs = recur(n.lhs)
          n
        }
        else {
          n.rhs = recur(n.rhs)
          n
        }
      }
    }
    
    def removeMax(n: Node[K, V]): (K, V, Node[K, V]) = {
      if (n.rhs == null) (n.key, n.value, n.lhs)
      else {
        val (k, v, node) = removeMax(n.rhs)
        n.rhs = node
        (k, v, n)
      }
    }
    
    root = recur(root)
    this
  }
  
  private def inorder(visit: (K, V) => Unit) = {
    def helper(n: Node[K, V]): Unit = if (n != null) {
      helper(n.lhs)
      visit(n.key, n.value)
      helper(n.rhs)
    }
    helper(root)
  }
}

object BSTMap {
  private class Node[K, V](var lhs: Node[K, V], var key: K, var value: V, var rhs: Node[K, V])
}