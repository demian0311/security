package com.neidetcher.security.chapter04

import org.junit.Test
import com.neidetcher.security.Util

class BaseRsaExampleTest {

  @Test def test(){
    val unit = new BaseRsaExample()

    val plainBytes = Array[Byte](0xbe.toByte, 0xef.toByte)
    Util.printBytes("PLN", plainBytes)

    for{
      encryptedBytes <- unit.encrypt(plainBytes)
      _ <- Some(Util.printBytes("ENC", encryptedBytes))
      decryptedBytes <- unit.decrypt(encryptedBytes)
      _ <- Some(Util.printBytes("DEC", decryptedBytes))
    } yield(decryptedBytes)
  }
}
