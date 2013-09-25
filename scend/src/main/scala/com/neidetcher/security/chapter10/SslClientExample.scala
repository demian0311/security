package com.neidetcher.security.chapter10

import javax.net.ssl.SSLSocketFactory
import com.neidetcher.security.Util

class SslClientExample(val destinationHost: String, val destinationPort: Int) {
  val socketFactory = SSLSocketFactory.getDefault
  val socket = socketFactory.createSocket(destinationHost, destinationPort)

  def sendAndReceive(message: String): String = {
    val out = socket.getOutputStream()
    val in = socket.getInputStream()

    out.write(Util.toByteArray(message))
    out.write('!')

    var ch: Byte = '\n'
    while (ch != '!'){
      ch = in.read().toByte
      print(ch.toChar)
    }
    ">>>"
  }
}
