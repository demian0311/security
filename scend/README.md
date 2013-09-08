I'm reading 'Beginning Cryptography in Java' and this is me playing
with APIs.

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
