#!/usr/bin/env bash

set -e

cd $(dirname $0)

label=$2
image="micro-message-thrift-service"
registry="registry.cn-shenzhen.aliyuncs.com/ydemo/micro-user-message-service"

if [[ -z "$label" ]]; then
  label=latest
fi

docker build -t ${image}:${label} .

if [[ "push" == $1 ]]; then
  tag=$(docker images -q $image:$label)
  docker tag ${tag} ${registry}:${label}
  docker push ${registry}:${label}
fi
