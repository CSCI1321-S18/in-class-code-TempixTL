package adt

import org.junit.Test
import org.junit.Assert._
import org.junit.Before
import scala.util.Random

class TestUAPriorityQueue {
  
  var queue: Queue[Int] = null
  
  @Before def makeQueue() = {
    queue = new UAPriorityQueue[Int](_ > _)
  }
  
  @Test def emtpy() = {
    assertTrue(queue.isEmpty)
  }
  
  @Test def enqueueDequeue() = {
    queue.enqueue(5)
    assertEquals(queue.peek, 5)
    assertFalse(queue.isEmpty)
    assertEquals(queue.dequeue(), 5)
  }
  
  @Test def enqueueDequeue15() = {
    for (i <- 0 until 15) queue.enqueue(i)
    for (i <- 14 to 0 by -1) assertEquals(i, queue.dequeue())
    assertTrue(queue.isEmpty)
  }
  
  @Test def enqueueDequeueArbitrary() = {
    for (_ <-  0 until 10) queue.enqueue(Random.nextInt())
    var lastDeq = queue.dequeue()
    for (_ <- 0 until 9) {
      val nextDeq = queue.dequeue()
      assertTrue(lastDeq > nextDeq)
      lastDeq = nextDeq
    }
  }
  
}