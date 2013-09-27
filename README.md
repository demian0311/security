I'm reading 'Beginning Cryptography in Java' and this is me playing
with APIs for a few of the examples but using Scala.

## Running SslClietnServerTest
- From the commandline run `sbt run`.  That will fine the only App in the project
which is an SSL server.
- From within an IDE remove the @Ignore from SslClientServerTest and run that.  If
you do that you should see some text being sent both ways.

## KeyStore and TrustStore
The book has you programatically creating keystores and truststores.  Although it 
might be cool to mess with it doesn't seem like you do that much in the real world.
Here are the commands I did to create the files used for the SSL example.

### To make the keystore
```
keytool -genkey -alias localhost -keyalg RSA -keystore chapter10-keystore.jks -keysize 2048
```

### To make the public key
```
keytool -export -keystore ./chapter10-keystore.jks -alias localhost -file chapter10-truststore.cer
```

### To make the trust store from the public key
```
keytool -import -file chapter10-public-key.cer -alias localhost -keystore chapter10-truststore.jks
```

### Referencing the *stores fromt he code.
Also to make the examples simpler to run I did this instead of commandline environment
variables to tell the JVM about these files.

```
System.setProperty("javax.net.ssl.trustStore", "src/main/resources/chapter10-truststore.jks")
System.setProperty("javax.net.ssl.trustStorePassword", "foobar")
```

## Unrestricted Policy Files
The JVM comes *hobbled* with respect to cryptography out of the box.
The stock setting is lowest common denominator for export restrictions.
To do anything serious you want to get the unrestricted policy files.

**Pro tip**: your IDE might be on a different JRE than your commandline, I 
had to do these steps for both Java 1.6 and Java 1.7.

- Download from Oracle [Java 1.6](http://www.oracle.com/technetwork/java/javase/downloads/jce-6-download-429243.html), [Java 1.7](http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html)
- Extract it
- Go to JAVA_HOME and copy the files in there
- Back up the original security policy files

``` bash
<JAVA_HOME>/lib/security>sudo cp US_export_policy.jar .original_US_export_policy.jar
<JAVA_HOME>/lib/security>sudo cp local_policy.jar .original_local_policy.jar
```

- Copy in the unrestricted policy files

``` bash
<JAVA_HOME>/lib/security>sudo cp <WHEREVER>/jce/US_export_policy.jar .
<JAVA_HOME>/lib/security>sudo cp <WHEREVER>/jce/local_policy.jar .
```
