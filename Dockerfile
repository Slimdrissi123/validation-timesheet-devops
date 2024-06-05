FROM openjdk:11
EXPOSE 8082
ADD target/*.jar *.jar
ENTRYPOINT ["java","-jar","/*.jar"]