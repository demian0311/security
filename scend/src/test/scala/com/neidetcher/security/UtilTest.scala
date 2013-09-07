package com.neidetcher.security

import org.junit.Test
import org.junit.Assert._

class UtilTest {
  @Test def test(){
    def startingString = "Hello World"
    val byteArray: Array[Byte] = Util.stringToByteArray(startingString)
    def string: String = Util.byteArrayToString(byteArray)
    assertEquals(startingString, string)
  }
}
