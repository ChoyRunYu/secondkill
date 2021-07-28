FROM openjdk:8-jre
WORKDIR /build
ADD ./target/secondkill-order-1.1.0.jar ./secondkill-order.jar
EXPOSE 8010
CMD sleep 40; java -jar secondkill-order.jar