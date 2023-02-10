FROM openjdk:17
EXPOSE 9000
ADD target/Jenkins-demo.jar Jenkins-demo.jar
ENTRYPOINT ["java", "-jar","/Jenkins-demo.jar"]