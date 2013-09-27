package com.neidetcher.security.chapter04

import javax.crypto.Cipher
import java.security.{KeyPair, KeyPairGenerator, SecureRandom, Security}
import org.bouncycastle.jce.provider.BouncyCastleProvider
import com.neidetcher.security.Util
import java.security.spec.RSAKeyGenParameterSpec

class RandomKeyRsaExample {
  Security.addProvider(new BouncyCastleProvider())

  private val secureRandom: SecureRandom = Util.getSecureRandom(false)

  private lazy val keyPair = {
    val keyPairGenerator = KeyPairGenerator.getInstance("RSA", "BC")
    //keyPairGenerator.initialize(256, secureRandom) // a small value
    keyPairGenerator.initialize(new RSAKeyGenParameterSpec(256, RSAKeyGenParameterSpec.F4)) // X.509 spec

    val keyPair: KeyPair = keyPairGenerator.generateKeyPair()

    println("*** Generated Key Pair ***")
    println(keyPair.getPublic.toString)
    println(keyPair.getPrivate.toString)
    println("**************************")

    keyPair
  }

  private lazy val encryptingCipher = {
    val encryptingCipher = Cipher.getInstance("RSA/None/NoPadding", "BC")
    encryptingCipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic, secureRandom)
    encryptingCipher
  }

  private lazy val decryptingCipher = {
    val decryptingCipher = Cipher.getInstance("RSA/None/NoPadding", "BC")
    decryptingCipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate, secureRandom)
    decryptingCipher
  }

  def encrypt(plainBytes: Array[Byte]): Option[Array[Byte]] = Some(encryptingCipher.doFinal(plainBytes))

  def decrypt(encryptedBytes: Array[Byte]): Option[Array[Byte]] = Some(decryptingCipher.doFinal(encryptedBytes))
}
