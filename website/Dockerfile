################################################################################
# FRONTEND CONTAINER                                                           #
#                                                                              #
# Runs the backend services for the application                                #
################################################################################
FROM tomcat:8.0-jre8
MAINTAINER Willem Meints <willem@mein-design.nl>

# Install the web application in the root of the tomcat server
COPY ./target/website-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
