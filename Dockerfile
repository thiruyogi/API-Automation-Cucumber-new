FROM ubuntu:latest
# Install OpenJDK-8
RUN apt-get update && \
    apt-get install -y apt-utils
RUN apt-get install -y openjdk-8-jdk && \
    apt-get install -y ant && \
    apt-get install -y  openssh-server && \
    apt-get clean;
# Fix certificate issues
RUN apt-get install ca-certificates-java && \
    apt-get clean && \
    update-ca-certificates -f;
# Setup JAVA_HOME -- useful for docker commandline
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME
#for installing maven
RUN apt-get update && \
    apt-get -y install maven
#for installing git
RUN apt-get update && \
    apt-get -y install git
#cloning the repository into the container
RUN mkdir -p /u01
COPY ./c3m-api-automation-cucumber /home/u01

#setting the work directory
WORKDIR /home/u01/c3m-api-automation-cucumber
RUN mvn clean install
