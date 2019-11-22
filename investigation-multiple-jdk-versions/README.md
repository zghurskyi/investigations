# Compiling
```shell script
mvn clean compile
```

# Packaging

```shell script
mvn clean package
```

# Checking Java version 

```shell script
javap -verbose -cp target/investigation-multiple-jdk-versions-0.0.1-SNAPSHOT-jdk8.jar io.zghurskyi.GreetingsClient | grep "major version"
```

```shell script
javap -verbose -cp target/investigation-multiple-jdk-versions-0.0.1-SNAPSHOT-jdk11.jar io.zghurskyi.GreetingsClient | grep "major version"
```
