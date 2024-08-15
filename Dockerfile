FROM amazoncorretto:17
ADD /target/demo.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]