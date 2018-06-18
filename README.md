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
  `{ "DEVICE_NAME": "My Unique Device Name",`
  `"IPCHECK_HOST": "www.google.com",`
  `"IPCHECK_RESOURCE": "/search?q=what+is+my+ip",`
  `"EMAIL_SERVER": "smtp.gmail.com",`
  `"EMAIL_PORT": 465,`
  `"EMAIL_USERNAME": "address@gmail.com",`
  `"EMAIL_PASSWORD": "password",`
  `"EMAIL_SENDER": "My Unique Device Name <address@gmail.com>",`
  `"EMAIL_RECIPIENT_LIST": [ "address@gmail.com" ],`
  `"EMAIL_TIMEOUT": 30000 }`

* Configure IP check frequency using Windows Task Scheduler  
  `java -jar "C:\jpip\app.jar" "C:\jpip"`

* All traffic across HTTPS and SMTPS

* Requires no open inbound ports
