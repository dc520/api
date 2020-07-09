FROM tomcat:10.0.0-M7-jdk8-openjdk-slim
COPY a.war /usr/local/tomcat/webapps
