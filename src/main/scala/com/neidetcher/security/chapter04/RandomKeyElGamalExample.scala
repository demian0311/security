package com.neidetcher.security.chapter04

import javax.crypto.Cipher
import com.neidetcher.security.Util
import java.security.{KeyPairGenerator, Security}
import org.bouncycastle.jce.provider.BouncyCastleProvider

class RandomKeyElGamalExample {
  Security.addProvider(new BouncyCastleProvider())

  private val algorithm = "ElGamal"
  private val provider = "BC"

  val secureRandom = Util.getSecureRandom(false)

  val keyPair = {
    val keyPairGenerator = KeyPairGenerator.getInstance(algorithm, provider)
    keyPairGenerator.initialize(256, secureRandom)
    keyPairGenerator.generateKeyPair()
  }

  lazy val encryptionCipher = {
    val encryptionCipher = Cipher.getInstance(s"${algorithm}/None/NoPadding", provider)
    encryptionCipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic, secureRandom)
    encryptionCipher
  }

  lazy val decryptionCipher = {
    val decryptionCipher = Cipher.getInstance(s"${algorithm}/None/NoPadding", provider)
    decryptionCipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate, secureRandom)
    decryptionCipher
  }

  def encrypt(plainBytes: Array[Byte]): Option[Array[Byte]] =
    Some(encryptionCipher.doFinal(plainBytes))

  def decrypt(encryptedBytes: Array[Byte]): Option[Array[Byte]] =
    Some(decryptionCipher.doFinal(encryptedBytes))
}
