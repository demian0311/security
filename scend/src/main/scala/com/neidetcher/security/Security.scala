package com.neidetcher.security

import javax.crypto._
import javax.crypto.spec._

class Security {
  // TODO-DLN: must be a less stupid way to create a literal array of bytes
  //                                  12345678
  val data8 = Util.stringToByteArray("HELLO WO")
  val data16 = Util.stringToByteArray("HELLO WORLD" * 4)

  def checkPolicyFiles(){
    val byteArray = Array[Byte]('0'.toByte, '1'.toByte, '2'.toByte, '3'.toByte, '4'.toByte)
    val bytesForKey = Util.stringToByteArray("FOO BAR")
    val key64: SecretKey = new SecretKeySpec(bytesForKey, "Blowfish")

    val cipher: Cipher = Cipher.getInstance("Blowfish/ECB/NoPadding")

    cipher.init(Cipher.ENCRYPT_MODE, key64)
    println("PLN: " + data8)
    val out = cipher.doFinal(data8)
    println("ENC: " + out) // don't understand, this is the same as data
    println("64bit passed")
  }
}
