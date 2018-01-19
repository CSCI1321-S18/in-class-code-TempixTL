package hitbox

class Point private(val x: Double, val y: Double) {

  def distanceTo(otherPoint: Point): Double = {
    val a = otherPoint.x - this.x
    val b = otherPoint.y - this.y
    Math.sqrt((a * a) + (b * b))
  }

}

object Point {
  
  def main(args: Array[String]) = {
    //Basic Point testing
    val pt1 = Point.zero
    val pt2 = Point(5, 5)
    println("Distance from pt1 to pt2 = " + (pt1 distanceTo pt2))
  }
  
  def apply(x: Double, y: Double): Point = {
    new Point(x, y)
  }
  
  def zero = new Point(0, 0)
  
}

