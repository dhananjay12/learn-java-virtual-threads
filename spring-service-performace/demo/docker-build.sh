mvn clean install
docker build --build-arg JAR_FILE=target/*.jar -t dhananjay12/demo-app-java-21:latest .