package com.neidetcher.security

import org.junit.Test
import org.junit.Assert._
import com.neidetcher.security.Util._
import java.security.SecureRandom

class UtilTest {
  @Test def testToHex(){
    val byteArray = toByteArray("Hello World")
    val result = toHex(byteArray)
    assertEquals("48656c6c6f20576f726c64", result)
  }

  @Test def testConversions(){
    def startingString = "Hello World"
    val byteArray: Array[Byte] = toByteArray(startingString)
    def string: String = Util.toString(byteArray)
    assertEquals(startingString, string)
  }

  @Test def testGetSecureRandom(){
    val secureRandom: SecureRandom = Util.getSecureRandom()
    assertEquals("java.security.SecureRandom", secureRandom.getClass.getName)

    val fixedRand: SecureRandom = Util.getSecureRandom(false)
    assertEquals("com.neidetcher.security.Util$FixedRand", fixedRand.getClass.getName)

    // TODO: test this some more when we understand how it's going to be used
  }
}
