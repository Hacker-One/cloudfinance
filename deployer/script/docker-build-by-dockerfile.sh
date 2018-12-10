#!/bin/bash

name=$1
mkdir -p /tmp/$name
mv /tmp/$name.tgz /tmp/$name/slug.tgz
cd /tmp/$name
tar zxvf slug.tgz

project=${name%%-*}
if [ $project == $name ]; then
  project=qloud
  img=$name
else
  img=${name#*-}
fi

ver=${2:-latest}
tag=reg.qloud.com/${project}/${img}:${ver}
docker build -t ${tag} .

curl -u "admin:Harbor12345" -X POST -H "Content-Type: application/json" -k "https://49.4.85.52/api/projects" -d '{"project_name":"'${project}'","public":1}'
docker push ${tag}
docker rmi ${tag}
