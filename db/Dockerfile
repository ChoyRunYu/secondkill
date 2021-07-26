FROM mysql:8.0.14
ENV TZ=Asia/Shanghai
RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY ./choy_ms.sql /docker-entrypoint-initdb.d
