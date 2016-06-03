FROM tomcat:8-jre8
MAINTAINER John Chen <johnweb@yeah.net> 

# WORKDIR /app
ADD target/spittr.war /usr/local/tomcat/webapps/spittr.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
