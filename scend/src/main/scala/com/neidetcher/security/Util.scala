package com.neidetcher.security

object Util {
  def toString(byteArray: Array[Byte]): String = {
    byteArray.map{_.toChar}.mkString
  }

  def toByteArray(stringIn: String): Array[Byte] = {
    stringIn.map{_.toByte}.toArray
  }
}
