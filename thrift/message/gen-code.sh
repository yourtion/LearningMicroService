#!/usr/bin/env bash

thrift --gen py -out ../../message-service message.thrift

thrift --gen java -out ../../message-thrift-api/src/main/java/ message.thrift
