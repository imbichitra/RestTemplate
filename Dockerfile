FROM openjdk:8
ADD target/rest-template.jar rest-template.jar
ENTRYPOINT ["java","-jar","rest-template.jar"]
EXPOSE 9090