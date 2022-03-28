FROM maven:3.6.3-openjdk-16
RUN mkdir PhotoUsersApi
WORKDIR PhotoUsersApi
COPY . .
RUN mvn package -Dmaven.test.skip=true
CMD ["java", ".jar", "target/PhotoUsersApi-0.0.1-SNAPSHOT.jar"]