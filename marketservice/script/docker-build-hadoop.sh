#!/bin/bash

name=$1

if [ ""$name == "" ];then
   exit 0
fi

mkdir -p /tmp/$name
mv /tmp/$name.tgz /tmp/$name/
cd /tmp/$name
tar -xf $name.tgz

project=${name%%-*}
ver=${2:-latest}

tmppath=/tmp/$name
templatepath=/qloudalgo/qloudalgo_template
buildpath=/qloudalgo/qloudalgo_build_model
if [ -d $buildpath ]; then
   rm -R $buildpath
fi
cp -R $templatepath $buildpath

cp -R ${tmppath}/target/standalone ${buildpath}/qloudalgo-model/

# build 
curl -u "admin:Harbor12345" -X POST -H "Content-Type: application/json" -k "https://49.4.85.52/api/projects" -d '{"project_name":"'${project}'","public":1}'

 cd ${buildpath}/qloudalgo-model
 tag=reg.qloud.com/${project}/qloudalgo-model:${ver}
 docker build -t ${tag} .

 docker push ${tag}
 docker rmi ${tag}
 
if [ ""$name != "" ]; then
   rm -R /tmp/$name
fi
