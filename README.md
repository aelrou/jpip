# jpip
Public IP address monitor and notify utility using Java on Windows

* Builds in [Java 10](http://www.oracle.com/technetwork/java/javase/downloads/index.html)  
  `JAVA_HOME - C:\Program Files\Java\jdk-10.0.1`  
  `JRE_HOME - C:\Program Files\Java\jre-10.0.1`  
  `PATH - C:\Program Files\Java\jre-10.0.1\bin`

* Build JAR with dependencies using [Maven](https://maven.apache.org/)  
  `M2_HOME - C:\Program Files\Maven`  
  `PATH - C:\Program Files\Maven\bin`  
  `C:\jpip>mvn clean package`

* Configure email variables in JSON config file  
  `"EMAIL_SENDER":"my@email.com"`

* Configure IP check frequency using Windows Task Scheduler  
  `java -jar "C:\jpip\app.jar" "C:\jpip"`

* Requires no open inbound ports

* All traffic across HTTPS and SMTPS
