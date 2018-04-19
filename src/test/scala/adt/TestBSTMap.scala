package adt

import org.junit._
import org.junit.Assert._

class TestImmutableAVLTreeMap {
  @Test def addGet: Unit = {
    val tm = new BSTMap("one" -> 1, "two" -> 2, "alpha" -> 0)((s1, s2) => s1.compareTo(s2))
    assertEquals(1, tm("one"))
    assertEquals(2, tm("two"))
    assertEquals(0, tm("alpha"))
  }

  @Test def addIter: Unit = {
    val tm = new BSTMap("one" -> 1, "two" -> 2, "alpha" -> 0)((s1, s2) => s1.compareTo(s2))
    val ans = Seq("alpha" -> 0, "one" -> 1, "two" -> 2)
    for (((mk, mv), (ak, av)) <- tm zip ans) {
      assertEquals(ak, mk)
      assertEquals(av, mv)
    }
  }

  @Test def bookTest1: Unit = {
    val nums = Array(5, 3, 1, 8, 7, 4, 2, 9, 0, 6)
    val tm = new BSTMap(nums.sorted.map(n => n.toString -> n): _*)((s1, s2) => s1.compareTo(s2))
    val snums = nums.sorted
    for ((n, (s, m)) <- snums zip tm) assertEquals(n, m)
  }

  @Test def bookTest3: Unit = {
    val nums = Array(5, 3, 1, 8, 7, 4, 2, 9, 0, 6)
    var tm = new BSTMap[String, Int]()((s1, s2) => s1.compareTo(s2))
    for (n <- nums) tm += n.toString -> n
    val snums = nums.sorted
    for ((n, (s, m)) <- snums zip tm) assertEquals(n, m)
  }

  @Test def bookTest4: Unit = {
    val nums = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)
    var tm = new BSTMap(nums.sorted.map(n => n.toString -> n): _*)((s1, s2) => s1.compareTo(s2))
    val snums = nums.sorted
    for ((n, (s, m)) <- snums zip tm) assertEquals(n, m)
  }
  
  @Test def remove: Unit = {
    val nums = Array.fill(100)(util.Random.nextInt(100)).distinct
    val map = new BSTMap(nums.map(n => n -> n.toString): _*)(_ - _)
    for (num <- nums) {
      assertTrue(map.contains(num))
      map -= num
      assertFalse(map.contains(num))
    }
  }
}
