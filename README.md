# Objective

It's the code repository of the OWASP cheatsheet [Authorization Testing Automation](https://www.owasp.org/index.php/Authorization_Testing_Automation).

# Build status

[![Build Status](https://travis-ci.org/righettod/poc-authz-testing.svg?branch=master)](https://travis-ci.org/righettod/poc-authz-testing)

# Content

The POC is composed of 2 parts:
* A web application exposing a simple REST web API using SpringBoot for the framework and JSON Web Token for access management.
* A set of integration tests in order automate the testing of the authorization.

The authorizations (2 dimensions ROLE x SERVICE) are described in the XML file named **authorization-matrix.xml** and this file is used as pivot file and input source for the integration tests.

All classes are fully documented.

The project is developed with Maven under IntelliJ IDEA Community Edition.

# Run the POC

Execute under linux this command `bash run-authorization-matrix-tests.sh`.

# Build

Execute this command to build a runnable version of the web application `mv clean package`.

The runnable jar will be created as file *target/poc-authz-testing-runnable.jar*.

Create a file named *jwt-secret.txt* containing a random string that will be used as secret for the signature of the JWT access token and store it in the same location than the runnable jar before to execute the jar.

```
$ cat jwt-secret.txt
zFsxstSjOSMhr3pBVqGBDaCk0zvzRQ4r9TtVnuLq-FkL2jANSo
```

```
$ java -jar target/poc-authz-testing-runnable.jar

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::  (v2.0.0.BUILD-SNAPSHOT)

[INFO ] - No active profile set, falling back to default profiles: default
[INFO ] - HV000001: Hibernate Validator 6.0.4.Final
[INFO ] - Tomcat initialized with port(s): 8080 (http)
[INFO ] - Starting service [Tomcat]
[INFO ] - Starting Servlet Engine: Apache Tomcat/8.5.23
[INFO ] - Initializing Spring embedded WebApplicationContext
...
```
