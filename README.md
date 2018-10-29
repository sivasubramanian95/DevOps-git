# How to create Lambda Service and run test

Introducing run AWS Lambda service on Amazon Web Service.

### Refer this list to build a Lambda function

I set up those software package and AWS services. Pushed README file how to set them up only Lambda, git repository, Java maven project on Linux. 

* [AWS] VPC, Security Group, Subnet 
* [AWS] RDS - created DB Schema and tables
* [AWS] Lambda
* [Git] Git repository
* [Java] Maven project including JDBC(mariaDB) Driver based on Ubuntu 16.04 LTS

## 1. Java maven project

### Packaging Java Maven project without IDE such as Eclipse or any

reference URI : https://docs.aws.amazon.com/lambda/latest/dg/java-create-jar-pkg-maven-no-ide.html

* 1.1 Check Maven version on Linux ( using Ubuntu command line )
```
root@test:~$sudo mvn -v
	-ba	sh: mvn: command not found
```

```
root@test:~$sudo apt list maven
	Listing... Done
	maven/xenial,xenial 3.3.9-3 all
```

* 1.2 Install Maven
```
root@test:~$sudo apt-get install maven
	Reading package lists... Done
	Building dependency tree       
	Reading state information... Done
	The following additional packages will be installed:
  	ant ant-optional ca-certificates-java default-jre-headless java-common junit junit4
  	...
```

* 1.3 Check Maven version after installing
```
root@test:~$ mvn -v
	Apache Maven 3.3.9
	Maven home: /usr/share/maven
	Java version: 1.8.0_181, vendor: Oracle Corporation
	Java home: /usr/lib/jvm/java-8-openjdk-amd64/jre
	Default locale: en_US, platform encoding: UTF-8
	OS name: "linux", version: "4.4.0-1066-aws", arch: "amd64", family: "unix"
```
* 1.4 Create project
You should have the following the directory :
```
project-dir/pom.xml
project-dir/src/main/java/RdsConnection.java (java class should be here)
``` 

You will have to be completely rewritten ```GroupID```, ```artifactId```,```name``` on the source.
pom.xml source as below :
```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>doc-examples</groupId>
  <artifactId>lambda-java-example</artifactId>
  <packaging>jar</packaging>
  <version>1.0-SNAPSHOT</version>
  <name>lambda-java-example</name>

  <dependencies>
    <dependency>
      <groupId>com.amazonaws</groupId>
      <artifactId>aws-lambda-java-core</artifactId>
      <version>1.1.0</version>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-shade-plugin</artifactId>
        <version>2.3</version>
        <configuration>
          <createDependencyReducedPom>false</createDependencyReducedPom>
        </configuration>
        <executions>
          <execution>
            <phase>package</phase>
            <goals>
              <goal>shade</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
</project>
```

If you had followed, Directory structure will be looks like this.
 ```
 root@test:~/rdsConnection$ tree -f
.
├── ./pom.xml
└── ./src
    └── ./src/main
        └── ./src/main/java
            └── ./src/main/java/rdsConnection
                └── ./src/main/java/rdsConnection/RdsConnection.java 
 ```

* 1.5 Packaging Java project.
Go to 'rdsConnection' directory and use the command.
```
 root@test:~/rdsConnection$ sudo mvn package
[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building RdsConnection 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ RdsConnection ---
...
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 2.195 s
[INFO] Finished at: 2018-10-29T20:11:44+09:00
[INFO] Final Memory: 18M/171M
[INFO] ------------------------------------------------------------------------
```

Maven has packaged the project below rdsConnection dir.
```
 root@test:~/rdsConnection$ tree -f
.
├── ./pom.xml
├── ./src
│   └── ./src/main
│       └── ./src/main/java
│           └── ./src/main/java/rdsConnection
│               └── ./src/main/java/rdsConnection/RdsConnection.java
└── ./target
    ├── ./target/classes
    │   └── ./target/classes/rdsConnection
    │       └── ./target/classes/rdsConnection/RdsConnection.class
    ├── ./target/maven-archiver
    │   └── ./target/maven-archiver/pom.properties
    ├── ./target/maven-status
    │   └── ./target/maven-status/maven-compiler-plugin
    │       └── ./target/maven-status/maven-compiler-plugin/compile
    │           └── ./target/maven-status/maven-compiler-plugin/compile/default-compile
    │               ├── ./target/maven-status/maven-compiler-plugin/compile/default-compile/createdFiles.lst
    │               └── ./target/maven-status/maven-compiler-plugin/compile/default-compile/inputFiles.lst
    ├── ./target/original-RdsConnection-1.0-SNAPSHOT.jar
    └── ./target/RdsConnection-1.0-SNAPSHOT.jar

12 directories, 8 files
```

Get the RdsConnection-1.0-SNAPSHOT.jar file and will upload to AWS Lambda console.

## 2. AWS Lambda service
### Create Lambda function and see log on CloudWatch

* 2.1 Create Lambda function
![AWS Lambda create function] (./img/Lambda_cre_fun.PNG)