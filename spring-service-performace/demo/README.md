## Build Demo Application

Using JIB maven plugin to build docker image

```bash
mvn clean -Dmaven.test.skip=true package jib:dockerBuild 
```
