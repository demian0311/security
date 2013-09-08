package com.neidetcher.security

object Util {
  private val digits = "0123456789abcdef"

  def toHex(byteArray: Array[Byte]): String =
    byteArray.map{ currVal: Byte =>
      val v: Int = currVal & 0xff
      "" + digits.charAt(v >> 4) + digits.charAt(v & 0xf)
    }.mkString


  def toString(byteArray: Array[Byte]): String =
    byteArray.map{_.toChar}.mkString


  def toByteArray(stringIn: String): Array[Byte] =
    stringIn.map{_.toByte}.toArray
}
