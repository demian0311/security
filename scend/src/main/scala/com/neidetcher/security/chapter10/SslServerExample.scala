package com.neidetcher.security.chapter10

import javax.net.ssl.SSLServerSocketFactory
import com.neidetcher.security.Util
import scala.io.Source

class SslServerExample(val port: Int) {
  val socketFactory = SSLServerSocketFactory.getDefault()
  val serverSocket = socketFactory.createServerSocket(port)
  val sslSocket = serverSocket.accept()

  def receiveAndSend(message: String): String = {
    println("-- session started --")

    val in = sslSocket.getInputStream()
    val out = sslSocket.getOutputStream()
    out.write(Util.toByteArray(message))

    val receivedMessage = Source.fromInputStream(in).getLines().mkString("\n")
    sslSocket.close()

    println("-- session ended --")

    receivedMessage
  }
}

object SslServerExample extends App {
  System.setProperty("javax.net.ssl.keyStore", "src/main/resources/chapter10-keystore.jks")
  System.setProperty("javax.net.ssl.keyStorePassword", "foobar")

  val sslServerExample = new SslServerExample(8012)
  sslServerExample.receiveAndSend("foo bar")
}


