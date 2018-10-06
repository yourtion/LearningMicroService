#!/usr/bin/env bash

thrift --gen java -out ../../user-thrift-api/src/main/java user.thrift
