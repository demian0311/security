package com.neidetcher.security

import org.junit.Test
import org.junit.Assert._

class UtilTest {
  @Test def test(){
    def startingString = "Hello World"
    val byteArray: Array[Byte] = Util.toByteArray(startingString)
    def string: String = Util.toString(byteArray)
    assertEquals(startingString, string)
  }
}
