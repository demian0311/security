package com.neidetcher.security

import org.junit.Test
import org.junit.Assert._
import com.neidetcher.security.Util._

class UtilTest {
  @Test def testToHex(){
    val byteArray = toByteArray("Hello World")
    val result = toHex(byteArray)
    assertEquals("48656c6c6f20576f726c64", result)
  }

  @Test def test(){
    def startingString = "Hello World"
    val byteArray: Array[Byte] = toByteArray(startingString)
    def string: String = Util.toString(byteArray)
    assertEquals(startingString, string)
  }
}
