#!/bin/bash

args=("$@")

mvn package

#hadoop jar target/*.jar "org.ktz.example.hadoopexample.$classPath" "hdfs://$HOSTNAME:9000/user/root/$targetFile"

hadoop jar target/*.jar $args