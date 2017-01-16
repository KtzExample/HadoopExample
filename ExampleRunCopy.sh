#!/usr/bin/env bash

class=$1

localFile=$2

outputPath=$3

mvn package

hadoop jar target/*.jar "org.ktz.example.hadoopexample.$class" $localFile "hdfs://$HOSTNAME:9000/user/root/$outputPath"