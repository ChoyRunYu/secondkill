FROM java:8
WORKDIR /build
ADD ./target/secondkill-zuul-1.1.0.jar ./secondkill-zuul.jar
EXPOSE 8000
CMD ["java", "-jar", "secondkill-zuul.jar"]