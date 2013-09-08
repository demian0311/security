package com.neidetcher.security

import javax.crypto.spec.SecretKeySpec
import javax.crypto.Cipher
import java.security.Security
import org.bouncycastle.jce.provider.BouncyCastleProvider

class Chapter02 {

  def doit(){
    Security.addProvider(new BouncyCastleProvider())

    val input: Array[Byte] = Array[Byte](
      0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77,
      0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77)

    val keyBytes: Array[Byte] = Array[Byte](
      0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
      0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
      0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17)

    println("keyBytes: " + keyBytes)
    println("keyBytes length: " + keyBytes.length)

    val key = new SecretKeySpec(keyBytes, "AES")
    val cipher: Cipher = Cipher.getInstance("AES/ECB/NoPadding", "BC")

    println("input size: " + input.length)
    println("input text: " + Util.toHex(input))

    // encryption pass
    cipher.init(Cipher.ENCRYPT_MODE, key)
    val cipherText: Array[Byte] = new Array[Byte](input.length)
    val ctLength = cipher.update(input, 0, input.length, cipherText, 0)
    println("cipher text: " + Util.toHex(cipherText) + " bytes: " + ctLength)

    // decryption pass
    cipher.init(Cipher.DECRYPT_MODE, key)
    val plainText: Array[Byte] = new Array[Byte](ctLength)
    var ptLength: Int = cipher.update(cipherText, 0, ctLength, plainText, 0)
    ptLength = ptLength + cipher.doFinal(plainText, ptLength)
    println("plain text: " + Util.toHex(plainText) + " bypes: " + ptLength)
  }
}
