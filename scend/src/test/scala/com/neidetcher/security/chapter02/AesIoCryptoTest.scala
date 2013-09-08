package com.neidetcher.security.chapter02

import org.junit.Test
import org.junit.Assert._
import com.neidetcher.security.Util

class AesIoCryptoTest {

  val aesIoCrypto = new AesIoCrypto()

  val plainText: Array[Byte] = Array[Byte](
    0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
    0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
    0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06)

  @Test def test(){
    val decryptedHex = for{
      _ <- Some(println("PLT: " + Util.toHex(plainText)))
      enc <- aesIoCrypto.encrypt(plainText)
      _ <- Some(println("ENC: " + Util.toHex(enc)))
      dec <- aesIoCrypto.decrypt(enc)
      _ <- Some(println("DEC: " + Util.toHex(dec)))
    } yield(Util.toHex(dec))

    assertEquals(Some(Util.toHex(plainText)), decryptedHex)
  }
}
