FROM java:8
WORKDIR /build
ADD ./target/secondkill-user-1.1.0.jar ./secondkill-user.jar
EXPOSE 8001
CMD sleep 40; java -jar secondkill-user.jar