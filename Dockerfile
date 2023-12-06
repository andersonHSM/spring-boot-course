FROM maven:3.9.5-eclipse-temurin-21

COPY entrypoint.sh /usr/local/bin/entrypoint.sh
RUN apt-get update && apt-get install vim dos2unix -y && dos2unix /usr/local/bin/entrypoint.sh && chmod +x /usr/local/bin/entrypoint.sh

#Start application
COPY pom.xml /tmp/pom.xml
COPY src/ /tmp/src

RUN mvn -B -f /tmp/pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:resolve
WORKDIR /usr/src/mymaven
COPY src/ /src
ENTRYPOINT ["/usr/local/bin/entrypoint.sh"]
CMD ["bash"]