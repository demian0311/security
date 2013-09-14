package com.neidetcher.security.chapter04

import org.junit.Test
import org.junit.Assert._
import com.neidetcher.security.Util

class RandomKeyElGamalExampleTest {
  @Test def test(){
    val unit = new RandomKeyElGamalExample()

    val plainBytes = Array(0xbe.toByte, 0xef.toByte)
    Util.printBytes("PLN", plainBytes)

    val result = for{
      enc <- unit.encrypt(plainBytes)
      _ <- Some(Util.printBytes("ENC", enc))
      dec <- unit.decrypt(enc)
      _ <- Some(Util.printBytes("DEC", dec))
    } yield dec

    assertEquals(Util.toHex(plainBytes), Util.toHex(result.get))
  }
}
