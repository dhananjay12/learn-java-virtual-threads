FROM openjdk:17.0.2-jdk-slim

RUN mkdir -p /usr/src/my_workdir

WORKDIR /usr/src/my_workdir

COPY src/main/java/com/example/preloom/DemoThread.java ./

#RUN javac DemoThread.java # With java 11 we dont have to compile. can be donr in 1 step

CMD java DemoThread.java