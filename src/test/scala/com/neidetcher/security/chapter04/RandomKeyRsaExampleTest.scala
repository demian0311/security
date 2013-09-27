package com.neidetcher.security.chapter04

import com.neidetcher.security.Util
import org.junit.Test
import org.junit.Assert._

class RandomKeyRsaExampleTest {
  @Test def test(){
    val unit = new RandomKeyRsaExample()

    val plainBytes = Array[Byte](0xbe.toByte, 0xef.toByte)
    Util.printBytes("PLN", plainBytes)

    val result = for{
      encryptedBytes <- unit.encrypt(plainBytes)
      _ <- Some(Util.printBytes("ENC", encryptedBytes))
      decryptedBytes <- unit.decrypt(encryptedBytes)
      _ <- Some(Util.printBytes("DEC", decryptedBytes))
    } yield decryptedBytes

    assertEquals(Util.toHex(plainBytes), Util.toHex(result.get))
  }

}
