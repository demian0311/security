package com.neidetcher.security

import org.junit.Test
import org.junit.Assert._

class AesCryptoTest {

  val chapter02 = new AesCrypto()

  val plainBytes: Array[Byte] = Array[Byte](
    0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77,
    0x88.toByte, 0x99.toByte, 0xaa.toByte, 0xbb.toByte,
    0xcc.toByte, 0xdd.toByte, 0xee.toByte, 0xff.toByte
  )

  @Test def testDoit2(){
    val results = for{
      enc <- chapter02.encrypt(plainBytes)
      dec <- chapter02.decrypt(enc)
    } yield (dec)

    results match {
      case Some(results) => assertEquals(Util.toHex(plainBytes), Util.toHex(results))
      case None =>  fail("didn't make it thru processing")
    }
  }
}
