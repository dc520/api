FROM tomcat:10.0.0-M7-jdk8-openjdk-slim
COPY ROOT.war /usr/local/tomcat/webapps/
