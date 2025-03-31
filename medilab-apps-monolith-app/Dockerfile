FROM openjdk:11 AS Build
RUN mkdir /opt/medilab-preclinic-monolith-boot AS Create Directory
WORKDIR /opt/medilab-preclinic-monolith-boot AS WORKING DIRectory
COPY target/medilab-morning-preclinic.war ${WORKDIR}
CMD [ "java","-jar","medilab-morning-preclinic.war" ]
