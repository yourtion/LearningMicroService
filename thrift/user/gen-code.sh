#!/usr/bin/env bash

cd $(dirname $0)

thrift --gen java -out ../../user-thrift-api/src/main/java user.thrift
