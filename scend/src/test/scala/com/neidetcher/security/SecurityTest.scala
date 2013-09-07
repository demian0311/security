package com.neidetcher.security

import org.junit.Test
import com.neidetcher.security.Security

class SecurityTest {
  @Test def checkPolicyFiles(){
    val s: Security = new Security()
    s.checkPolicyFiles()
  }

}
