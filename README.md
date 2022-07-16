# learn-java-virtual-threads

Wiki - https://wiki.openjdk.org/display/loom/Main

Early-Access Builds - https://jdk.java.net/loom/

Download latest JDK Loom depending upon platform. Unzip or Untar to a directory. 

## Test Locally

For java 17/19, set `-Xss1M -Xmx1G` VM Arguments to simulate `java.lang.OutOfMemoryError: unable to create native thread`.

For java 19, if using early build you will have to enable preview features, so set these as VM arguments `-Xss1M -Xmx1G --enable-preview`

#### Docker
Change the Dockerfile to the java class you want to run. Build using
```
docker build -t java-pre-loom .
```
Run 
```
docker run -it --rm --cpus="1.0" --memory="1g" -e NUM_THREADS=100000 java-pre-loom
```