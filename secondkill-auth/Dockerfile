FROM java:8
WORKDIR /build
ADD ./target/secondkill-auth-1.1.0.jar ./secondkill-auth.jar
EXPOSE 8002
CMD ["java", "-jar", "secondkill-auth.jar"]