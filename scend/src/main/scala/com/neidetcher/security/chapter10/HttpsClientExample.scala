package com.neidetcher.security.chapter10

import javax.net.ssl.{HttpsURLConnection, TrustManagerFactory, KeyManagerFactory, SSLContext}
import java.net.URL

class HttpsClientExample {

  def createSslContext(): SSLContext = {
    val keyManagerFactory = KeyManagerFactory.getInstance("SunX509")
    val trustManagerFactory = TrustManagerFactory.getInstance("SunX509")

    val sslContext = SSLContext.getInstance("TLS")
    sslContext.init(
      null, //keyManagerFactory.getKeyManagers(),
      null, //trustManagerFactory.getTrustManagers(),
      null) // does this just send in the default random?

    sslContext
  }

  def connect(urlString: String): Option[String]= {
    val sslContext = createSslContext()
    val sslSocketFactory = sslContext.getSocketFactory()

    val url = new URL(urlString)
    val httpsUrlConnection: HttpsURLConnection = url.openConnection().asInstanceOf[HttpsURLConnection]
    httpsUrlConnection.setSSLSocketFactory(sslSocketFactory)
    httpsUrlConnection.connect()

    val in = httpsUrlConnection.getInputStream()
    val stringOut = scala.io.Source.fromInputStream(in).getLines().mkString("\n")
    Some(stringOut)
  }

}
