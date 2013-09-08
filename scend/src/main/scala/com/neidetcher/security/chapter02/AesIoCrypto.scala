package com.neidetcher.security.chapter02

import javax.crypto.spec.{IvParameterSpec, SecretKeySpec}
import javax.crypto.{CipherOutputStream, CipherInputStream, Cipher}
import com.neidetcher.security.Util
import java.security.Security
import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.io.{ByteArrayOutputStream, ByteArrayInputStream}

class AesIoCrypto {

  Security.addProvider(new BouncyCastleProvider())

  val keyBytes = Array[Byte](
    0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
    0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
    0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17)

  val ivBytes = Array[Byte](
    0x00, 0x01, 0x02, 0x03, 0x00, 0x01, 0x02, 0x03,
    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01)

  val key = new SecretKeySpec(keyBytes, "AES")
  val ivParameterSpec = new IvParameterSpec(ivBytes)
  val cipher = Cipher.getInstance("AES/CTR/NoPadding", "BC")

  def encrypt(input: Array[Byte]): Option[Array[Byte]] = {
    cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec)

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
    // we share some state here, this is not safe
    cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec)

    val byteArrayOutputStream = new ByteArrayOutputStream()
    val cipherOutputStream = new CipherOutputStream(byteArrayOutputStream, cipher)

    cipherOutputStream.write(encryptedByteArray)
    cipherOutputStream.close()

    Some(byteArrayOutputStream.toByteArray)
  }
}
