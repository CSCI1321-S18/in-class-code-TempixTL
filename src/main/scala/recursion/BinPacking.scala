package recursion

object BinPacking extends App {
  def packBins(items: Array[Int], bins: Array[Int]): Boolean = {
    
    def helper(i: Int): Boolean = {
      if (i >= items.length) true
      else {
        var ret = false
        for (j <- bins.indices) {
          if (items(i) <= bins(j)) {
            bins(j) -= items(i)
            ret ||= helper(i+1)
            bins(j) += items(i)
          }
        }
        ret
      }
    }
    
    def helper2(i: Int): Boolean = {
      if (i >= items.length) true
      else {
        bins.indices.exists {j =>
          if (items(i) <= bins(j)) {
            bins(j) -= items(i)
            val h = helper2(i+1)
            bins(j) += items(i)
            h
          } else false
        }
      }
    }
    
    helper(0)
  }
  
  println(packBins(Array(6,3,3),Array(5,7)))
}