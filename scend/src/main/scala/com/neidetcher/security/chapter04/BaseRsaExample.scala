package com.neidetcher.security.chapter04

import javax.crypto.Cipher
import java.security.{Security, KeyFactory}
import java.security.spec.{RSAPrivateKeySpec, RSAPublicKeySpec}
import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.math.BigInteger

class BaseRsaExample {
  Security.addProvider(new BouncyCastleProvider())

  val keyFactory = KeyFactory.getInstance("RSA", "BC")

  val decryptingCipher = {
    val privKeySpec = new RSAPrivateKeySpec(
      new BigInteger("d46f473a2d746537de2056ae3092c451", 16),
      new BigInteger("57791d5430d593164082036ad8b29fb1", 16))

    val privKey = keyFactory.generatePrivate(privKeySpec)

    val decryptingCipher = Cipher.getInstance("RSA/None/NoPadding", "BC")
    decryptingCipher.init(Cipher.DECRYPT_MODE, privKey)
    decryptingCipher
  }

  lazy val encryptingCipher = {
    val pubKeySpec = new RSAPublicKeySpec(
      new BigInteger("d46f473a2d746537de2056ae3092c451", 16),
      new BigInteger("11", 16))

    val pubKey = keyFactory.generatePublic(pubKeySpec)

    val encryptingCipher = Cipher.getInstance("RSA/None/NoPadding", "BC")
    encryptingCipher.init(Cipher.ENCRYPT_MODE, pubKey)
    encryptingCipher
  }

  def encrypt(plainBytes: Array[Byte]): Option[Array[Byte]] =
    Some(encryptingCipher.doFinal(plainBytes))

  def decrypt(encryptedBytes: Array[Byte]): Option[Array[Byte]] =
    Some(decryptingCipher.doFinal(encryptedBytes))
}
