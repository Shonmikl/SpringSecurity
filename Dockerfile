FROM openjdk:11
COPY target/classes/com/mikhailegorov/spring/security/ /tmp
WORKDIR /tmp
CMD java com
