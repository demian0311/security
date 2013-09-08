package com.neidetcher.security.chapter02

import javax.crypto.spec.{IvParameterSpec, SecretKeySpec}
import javax.crypto.{CipherOutputStream, CipherInputStream, Cipher}
import java.security.Security
import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.io.{ByteArrayOutputStream, ByteArrayInputStream}

class AesIoCrypto {

  Security.addProvider(new BouncyCastleProvider())

  /**
    * I don't know how expensive this operation is but it prevents
    * us from having a shared Cipher that has different MODEs
    * set on it.
    */
  def getCipher(mode: Int): Cipher = {

    val keyBytes = Array[Byte](
      0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
      0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
      0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17)
    val key = new SecretKeySpec(keyBytes, "AES")

    val ivBytes = Array[Byte](
      0x00, 0x01, 0x02, 0x03, 0x00, 0x01, 0x02, 0x03,
      0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01)
    val ivParameterSpec = new IvParameterSpec(ivBytes)

    val cipher = Cipher.getInstance("AES/CTR/NoPadding", "BC")
    cipher.init(mode, key, ivParameterSpec)
    cipher
  }

  def encrypt(input: Array[Byte]): Option[Array[Byte]] = {
    val cipher = getCipher(Cipher.ENCRYPT_MODE)

    val byteArrayInputStream = new ByteArrayInputStream(input)
    val cipherInputStream = new CipherInputStream(byteArrayInputStream, cipher)
    val byteArrayOutputStream = new ByteArrayOutputStream()

    // TODO-DLN: this is horrible
    var ch: Int = cipherInputStream.read()
    while(ch != -1){
      byteArrayOutputStream.write(ch)
      ch = cipherInputStream.read()
    }

    val encryptedByteArray = byteArrayOutputStream.toByteArray()

    Some(encryptedByteArray)
  }

  def decrypt(encryptedByteArray: Array[Byte]): Option[Array[Byte]] = {
    val cipher = getCipher(Cipher.DECRYPT_MODE)

    val byteArrayOutputStream = new ByteArrayOutputStream()
    val cipherOutputStream = new CipherOutputStream(byteArrayOutputStream, cipher)

    cipherOutputStream.write(encryptedByteArray)
    cipherOutputStream.close()

    Some(byteArrayOutputStream.toByteArray)
  }
}
