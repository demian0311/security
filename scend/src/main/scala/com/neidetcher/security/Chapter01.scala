package com.neidetcher.security

import javax.crypto._
import javax.crypto.spec._
import com.neidetcher.security.Util._
import java.security.{Provider, Security => JavaSecurity}
import org.bouncycastle.jce.provider.BouncyCastleProvider
import scala.language.implicitConversions
import scala.collection.JavaConversions._


class Chapter01 {

  def listProviderCapabilities(providerName: String): Boolean = {
    JavaSecurity.addProvider(new BouncyCastleProvider())
    val provider: Provider = JavaSecurity.getProvider(providerName)
    val it: scala.collection.Iterator[AnyRef] = provider.keySet().iterator()
    while(it.hasNext){
      println(it.next())
    }
    true
  }

  def simpleProviderTest(providerName: String): String = {
    // alternative is to edit java.security file
    JavaSecurity.addProvider(new BouncyCastleProvider())

    def postFix = if (JavaSecurity.getProvider(providerName) != null) {
      "is installed"
    } else {
      "not installed"
    }
    providerName + " " + postFix
  }

  def simplePolicyTest(): Boolean = {
    val cipher: Cipher = Cipher.getInstance("Blowfish/ECB/NoPadding")

    val key64: SecretKey = new SecretKeySpec(toByteArray("01234567"), "Blowfish")
    cipher.init(Cipher.ENCRYPT_MODE, key64)
    println("PLN: " + toByteArray("HELLO WO"))
    println("64 bit test: passed")

    val key192 = new SecretKeySpec(toByteArray("0123456789ABCDEF"), "Blowfish")
    cipher.init(Cipher.ENCRYPT_MODE, key192)
    cipher.doFinal(toByteArray("HELLO WORLD FOOB"))
    println("192 bit test: passed")

    println("tests completed")

    true
  }
}
