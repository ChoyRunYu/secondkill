FROM openjdk:8-jre
WORKDIR /build
ADD ./target/secondkill-auth-1.1.0.jar ./secondkill-auth.jar
EXPOSE 8002
CMD sleep 40; java -jar secondkill-auth.jar