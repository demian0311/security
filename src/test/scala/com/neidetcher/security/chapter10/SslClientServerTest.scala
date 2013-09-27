package com.neidetcher.security.chapter10

import org.junit.{Ignore, Test}

/** integration test */
class SslClientServerTest {
  /** I had to do this:
    * - to make the keystore
    * keytool -genkey -alias localhost -keyalg RSA -keystore chapter10-keystore.jks -keysize 2048
    *
    * - to make the public key
    * keytool -export -keystore ./chapter10-keystore.jks -alias localhost -file chapter10-truststore.cer
    *
    * - to make the trust store from the public key
    * keytool -import -file chapter10-public-key.cer -alias localhost -keystore chapter10-truststore.jks
    */
  //@Test
  @Ignore("you need to `sbt run` the project and then run this from an IDE to see it in action")
  def test{
    // the trust store
    System.setProperty("javax.net.ssl.trustStore", "src/main/resources/chapter10-truststore.jks")
    System.setProperty("javax.net.ssl.trustStorePassword", "foobar")

    println(">> creating client")
    val sslClientExample = new SslClientExample("localhost", 8012)

    println(">> getting data from server")
    val dataFromServer = sslClientExample.sendAndReceive("hello world")
    println("from server: " + dataFromServer)
  }
}
