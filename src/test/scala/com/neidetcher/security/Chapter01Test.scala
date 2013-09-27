package com.neidetcher.security

import org.junit.Test
import org.junit.Assert._

class Chapter01Test {
  val security: Chapter01 = new Chapter01()

  @Test def listProviderCapabilities(){
    assertTrue(security.listProviderCapabilities("BC"))
  }

  @Test def simpleProviderTest(){
    assertEquals("BC is installed", security.simpleProviderTest("BC"))
  }

  @Test def simplePolicyTest(){
    assertTrue(security.simplePolicyTest())
  }
}
