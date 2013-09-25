package com.neidetcher.security.chapter10

import javax.net.ssl.SSLSocketFactory
import com.neidetcher.security.Util

class SslClientExample(val destinationHost: String, val destinationPort: Int) {
  val socketFactory = SSLSocketFactory.getDefault
  val socket = socketFactory.createSocket(destinationHost, destinationPort)

  def sendAndReceive(message: String): String = {
    val out = socket.getOutputStream()
    out.write(Util.toByteArray(message))
    out.write('!')

    val in = socket.getInputStream()
    scala.io.Source.fromInputStream(in).getLines().mkString("\n")
  }


}
