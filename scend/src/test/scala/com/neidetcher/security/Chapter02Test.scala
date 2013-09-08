package com.neidetcher.security

import org.junit.Test
import org.junit.Assert._

class Chapter02Test {

  val chapter02 = new Chapter02()

  @Test def testDoit(){
    val plainBytes: Array[Byte] = Array[Byte](
      0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77,
      0x88.toByte, 0x99.toByte, 0xaa.toByte, 0xbb.toByte,
      0xcc.toByte, 0xdd.toByte, 0xee.toByte, 0xff.toByte
    )

    println("PLN: " + Util.toHex(plainBytes))
    val encryptedBytes:Array[Byte] = chapter02.encrypt(plainBytes)
    println("ENC: " + Util.toHex(encryptedBytes))

    assertNotEquals(Util.toHex(plainBytes), Util.toHex(encryptedBytes))

    val decryptedBytes = chapter02.decrypt(encryptedBytes)
    println("DEC: " + Util.toHex(decryptedBytes))

    assertEquals(Util.toHex(plainBytes), Util.toHex(decryptedBytes))
  }

}
