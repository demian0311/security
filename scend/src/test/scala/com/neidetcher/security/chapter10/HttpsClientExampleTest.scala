package com.neidetcher.security.chapter10

import org.junit.Test
import org.junit.Assert._

class HttpsClientExampleTest {

  @Test def testConnect(){
    println("hello world")

    val httpsClientExample = new HttpsClientExample()
    httpsClientExample.connect("https://google.com") match {
      case Some(pageContents) => {
        println("pageContents: " + pageContents.substring(0, 40))
      }
      case None => {
        fail("couldn't get page contents")
      }
    }

  }

}
