FROM openjdk:8-jre

MAINTAINER benwang <benwang@linkdoc.com>

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

VOLUME /app

ADD *.jar /app/lkai-rud.jar

CMD ["java", "-Xmx256m", "-Xms256m", "-Xmn90m", "-Djava.security.egd=file:/dev/./urandom", "-Duser.timezone=GMT+8", "-jar", "/app/lkai-rud.jar"]

EXPOSE 8000
