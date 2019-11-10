#!/bin/bash

JAR=$(ls -1 /opt/*.jar | tail -n 1)

JAVA_OPTS="${EXTRA_JAVA_OPTS} -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:+UseSerialGC"

echo Running java ${JAVA_OPTS} -jar ${JAR}
exec java ${JAVA_OPTS} -jar ${JAR}