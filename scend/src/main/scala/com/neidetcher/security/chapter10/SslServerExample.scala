package com.neidetcher.security.chapter10

import javax.net.ssl.SSLServerSocketFactory
import com.neidetcher.security.Util

class SslServerExample(val port: Int) {
  val socketFactory = SSLServerSocketFactory.getDefault()
  val serverSocket = socketFactory.createServerSocket(port)
  val sslSocket = serverSocket.accept()

  def receiveAndSend(message: String): String = {
    println("-- session started --")
    val in = sslSocket.getInputStream()

    var ch = 0
    while (ch != '!'){
      ch = in.read().toByte
      print(ch.toChar)
    }

    val out = sslSocket.getOutputStream()
    out.write(Util.toByteArray(message))

    sslSocket.close()
    println("-- session closed --")
    "<<<"
  }
}

object SslServerExample extends App {
  System.setProperty("javax.net.ssl.keyStore", "src/main/resources/chapter10-keystore.jks")
  System.setProperty("javax.net.ssl.keyStorePassword", "foobar")

  val sslServerExample = new SslServerExample(8012)
  val fromClient = sslServerExample.receiveAndSend("foo bar!")
  println("from client: " + fromClient)
}


