package multithreading

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

object ThreeActorCountdown {
  case class CountDown(to: Int)
  
  class CountActor extends Actor {
    def receive = {
      case CountDown(num) => {
        println(num)
      }
    }
  }
  
  val system = ActorSystem("ThreeActorCountdown")
  system.actorOf(Props(new CountActor), "Actor1")
  
}