package com.neidetcher.security.chapter02

import javax.crypto.{SecretKeyFactory, Cipher}
import javax.crypto.spec.{PBEKeySpec, IvParameterSpec, SecretKeySpec}
import java.security.Security
import org.bouncycastle.jce.provider.BouncyCastleProvider

class PasswordBasedEncryption(val password: String) {

  Security.addProvider(new BouncyCastleProvider())

  val keyBytes = Array[Byte](
    0x73, 0x2f, 0x2d, 0x33, 0xc8.toByte, 0x01, 0x73,
    0x2b, 0x72, 0x06, 0x75, 0x6c, 0xbd.toByte, 0x44,
    0xf9.toByte, 0xc1.toByte, 0xc1.toByte, 0x03, 0xdd.toByte,
    0xd9.toByte, 0x7c, 0x7c, 0xbe.toByte, 0x8e.toByte)

  val ivBytes = Array[Byte](
    0xb0.toByte, 0x7b, 0xf5.toByte, 0x22, 0xc8.toByte,
    0xd6.toByte, 0x08, 0xb8.toByte )

  def encrypt(plainTextByteArray: Array[Byte]): Option[Array[Byte]] = {
    val encryptingCipher = Cipher.getInstance("DESede/CBC/PKCS7Padding", "BC")
    encryptingCipher.init(
      Cipher.ENCRYPT_MODE,
      new SecretKeySpec(keyBytes, "DESede"),
      new IvParameterSpec(ivBytes)
      )
    val encryptedByteArray = encryptingCipher.doFinal(plainTextByteArray)
    Some(encryptedByteArray)
  }

  def decrypt(encryptedByteArray: Array[Byte]): Option[Array[Byte]] = {
    val encName = "PBEWithSHAAnd3KeyTripleDES"

    val passwordCharArray = password.toCharArray()
    val salt = Array[Byte](0x7d, 0x60, 0x43, 0x5f, 0x02, 0xe9.toByte, 0xe0.toByte, 0xae.toByte)
    val iterationCount = 2048

    val pbeSpec: PBEKeySpec = new PBEKeySpec(passwordCharArray, salt, iterationCount)

    val secretKeyFactory = SecretKeyFactory.getInstance(encName, "BC")

    val decryptingCipher = Cipher.getInstance(encName, "BC")
    val key = secretKeyFactory.generateSecret(pbeSpec)

    decryptingCipher.init(Cipher.DECRYPT_MODE, key)
    val decryptedByteArray = decryptingCipher.doFinal(encryptedByteArray)

    Some(decryptedByteArray)
  }
}
