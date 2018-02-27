package networking

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintStream
import java.net.ServerSocket

object Server extends App {
  
  val ss = new ServerSocket(7707)
  
  val sock = ss.accept()
  val in = new BufferedReader( new InputStreamReader(sock.getInputStream))
  val out = new PrintStream(sock.getOutputStream)
  
  out.println("What is your name?")
  val name = in.readLine
  
  var input = ""
  while (input != ":quit") {
    if (in.ready) {
      input = in.readLine
      out.println(name + ": "+input)
    }
    Thread.sleep(100)
  }
  
}