#!/usr/bin/env bash

cd $(dirname $0)

thrift --gen py -out ../../message-service message.thrift

thrift --gen java -out ../../message-thrift-api/src/main/java/ message.thrift
