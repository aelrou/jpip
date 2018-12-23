# jpip
Public IP address monitor and notify utility using Java on Windows

* Builds in [Java 11](http://www.oracle.com/technetwork/java/javase/downloads/index.html)  
  `JAVA_HOME - C:\Program Files\Java\jdk-11.0.1`  
  `JRE_HOME - C:\Program Files\Java\jdk-11.0.1` if _C:\Program Files\Java\jre1.8.0_191_ is wrong version  
  `PATH - C:\Program Files\Java\jdk-11.0.1` if _C:\Program Files (x86)\Common Files\Oracle\Java\javapath_ is wrong version

* Build JAR with dependencies using [Maven](https://maven.apache.org/)  
  `M2_HOME - C:\Program Files\Maven`  
  `PATH - C:\Program Files\Maven\bin`  
  `C:\jpip>mvn clean package`

* Configure email variables in JSON config file  
  `"EMAIL_SERVER": "smtp.server.com",`  
  `"EMAIL_PROTOCOL": "SSL",` _or TLS_  
  `"EMAIL_PORT": 465,` _common TLS ports: 25, 587_  
  `"EMAIL_USERNAME": "sender@address.com",`  
  `"EMAIL_PASSWORD": "password",`  
  `"EMAIL_SENDER": "My Unique Device Name < sender@address.com >",`  
  `"EMAIL_RECIPIENT_LIST": [ "recipient-1@address.com", "recipient-2@address.com" ],`

* Configure IP check frequency using Windows Task Scheduler  
  `java -jar "C:\jpip\app.jar" "C:\jpip"`

* All traffic across HTTPS and SMTPS

* Requires no open inbound ports
