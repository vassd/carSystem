FROM openjdk:21-jdk-oracle
COPY target/system-0.0.1.jar system-0.0.1.jar
ENTRYPOINT ["java","-jar","/system-0.0.1.jar"]