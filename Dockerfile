FROM openjdk:17-oracle
MAINTAINER Shubham Varshney

ENV JAVA_HOME="/usr/lib/jvm/default-jvm/"

ENV PATH=$PATH:${JAVA_HOME}/bin
ADD build/libs/student-0.0.1.jar student.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "student.jar"]