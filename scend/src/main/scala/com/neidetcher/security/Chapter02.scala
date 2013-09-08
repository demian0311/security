package com.neidetcher.security

import javax.crypto.spec.SecretKeySpec
import javax.crypto.Cipher
import java.security.Security
import org.bouncycastle.jce.provider.BouncyCastleProvider

/**
 * This uses a hard-coded symmetric key.
 */
class Chapter02 {

  Security.addProvider(new BouncyCastleProvider())

  val keyBytes: Array[Byte] = Array[Byte](
    0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
    0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
    0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17)

  val key = new SecretKeySpec(keyBytes, "AES")
  val cipher: Cipher = Cipher.getInstance("AES/ECB/NoPadding", "BC")

  def encrypt(input: Array[Byte]): Option[Array[Byte]] = {
    cipher.init(Cipher.ENCRYPT_MODE, key)

    val cipherText: Array[Byte] = new Array[Byte](input.length)
    val numBytesUpdated = cipher.update(input, 0, input.length, cipherText, 0)
    val numBytesFinal = cipher.doFinal(cipherText, numBytesUpdated)

    if(numBytesFinal + numBytesUpdated == input.length){
      Some(cipherText)
    } else {
      None
    }
  }

  def decrypt(encryptedBytes: Array[Byte]): Option[Array[Byte]] = {
    cipher.init(Cipher.DECRYPT_MODE, key)

    val plainText: Array[Byte] = new Array[Byte](encryptedBytes.length)
    val numBytesUpdated = cipher.update(encryptedBytes, 0, encryptedBytes.length, plainText, 0)
    val numBytesFinal = cipher.doFinal(plainText, numBytesUpdated)

    if(numBytesFinal + numBytesUpdated == encryptedBytes.length){
      Some(plainText)
    } else {
      None
    }
  }
}
