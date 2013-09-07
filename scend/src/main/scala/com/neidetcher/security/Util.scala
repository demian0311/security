package com.neidetcher.security

object Util {
  def byteArrayToString(byteArray: Array[Byte]): String = {
    byteArray.map{_.toChar}.mkString
  }

  def stringToByteArray(stringIn: String): Array[Byte] = {
    stringIn.map{_.toByte}.toArray
  }
}
