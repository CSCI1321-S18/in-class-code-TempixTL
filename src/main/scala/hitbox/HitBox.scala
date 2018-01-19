package hitbox

class HitBox private(val x: Double, val y: Double,
                     val width: Double, val height: Double,
                     val anchorX: Double = 0.5, val anchorY: Double = 0.5) {
  
  def origin = Point(x, y)
  def anchorPoint = Point(anchorX, anchorY)
  
  def leftSide = origin.x - (width * anchorPoint.x)
  def rightSide = origin.x + (width * (1 - anchorPoint.x))
  def bottom = origin.y - (height * anchorPoint.y)
  def top = origin.y + (height * (1 - anchorPoint.y))
  def area = width * height
  
  def intersects(otherBox: HitBox): Boolean = {
	  if (this.leftSide >= otherBox.rightSide || this.rightSide <= otherBox.leftSide ||
	      this.top <= otherBox.bottom || this.bottom >= otherBox.top) false
	  else true
	}
  
}

object HitBox {
  
  def main(args: Array[String]) = {
    //Basic HitBox testing
    val box1 = HitBox(Point.zero, 10, 10)
	  val box2 = HitBox(5, 5, 10, 10)
	  println("Does box1 intersect box2? " + box1.intersects(box2))
  }
  
  def apply(x: Double, y: Double, width: Double, height: Double): HitBox = {
    new HitBox(x, y, width, height)
  }
  
  def apply(origin: Point, width: Double, height: Double): HitBox = {
    new HitBox(origin.x, origin.y, width, height)
  }
  
  def apply(origin: Point, width: Double, height: Double, anchorPoint: Point): HitBox = {
    new HitBox(origin.x, origin.y, width, height, anchorPoint.x, anchorPoint.y)
  }
  
  def zero = new HitBox(0, 0, 0, 0)
  
}