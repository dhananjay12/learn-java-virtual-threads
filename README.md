# learn-java-virtual-threads

Wiki - https://wiki.openjdk.org/display/loom/Main

Early-Access Builds - https://jdk.java.net/loom/

Download latest JDK Loom depending upon platform. Unzip or Untar to a directory. 

## Test Locally
### Java Pre Loom

Change the Dockerfile to the java class you want to run. Build using
```
docker build -t java-pre-loom .
```
Run 
```
docker run -it --rm --cpus="1.0" --memory="1g" -e NUM_THREADS=100000 java-pre-loom
```