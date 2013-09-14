package com.neidetcher.security

import java.security.{Security, MessageDigest, SecureRandom}
import org.bouncycastle.jce.provider.BouncyCastleProvider

object Util {
  private val digits = "0123456789abcdef"

  def toHex(byteArray: Array[Byte]): String =
    byteArray.map{ currVal: Byte =>
      val v: Int = currVal & 0xff
      "" + digits.charAt(v >> 4) + digits.charAt(v & 0xf)
    }.mkString

  def printBytes(label: String, byteArray: Array[Byte]) = {
    println(s"${label}: ${Util.toHex(byteArray)}")
  }

  def toString(byteArray: Array[Byte]): String =
    byteArray.map{_.toChar}.mkString

  def toByteArray(stringIn: String): Array[Byte] =
    stringIn.map{_.toByte}.toArray

  /**
   * This has some lame, non-idiomatic code in it but it isn't meant
   * to be used in any sort of production context.
   */
  protected class FixedRand extends SecureRandom {

    Security.addProvider(new BouncyCastleProvider())

    val sha: MessageDigest = MessageDigest.getInstance("SHA-1", "BC")
    var state: Array[Byte] = sha.digest()

    override def nextBytes(bytes: Array[Byte]) = {
      var off = 0

      sha.update(state)

      while(off < bytes.length){
        state = sha.digest()

        if(bytes.length - off > state.length){
          System.arraycopy(state, 0, bytes, off, state.length)
        } else {
          System.arraycopy(state, 0, bytes, off, bytes.length - off)
        }

        off = off + state.length
        sha.update(state)
      }
    }
  }


  def getSecureRandom(real: Boolean = true): SecureRandom = {
    if(real) {
      new SecureRandom()
    } else {
      println("****************************************")
      println("* USING INSECURE RANDOM IMPLEMENTATION *")
      println("****************************************")
      new FixedRand()
    }
  }
}
