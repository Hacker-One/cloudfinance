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
buildpath=/qloudalgo/qloudalgo_build
if [ -d $buildpath ]; then
   rm -R $buildpath
fi
cp -R $templatepath $buildpath

cp ${tmppath}/deploy/oryx-batch/target/oryx-batch-4.0.0.jar ${buildpath}/qloudalgo/
cp ${tmppath}/deploy/oryx-speed/target/oryx-speed-4.0.0.jar ${buildpath}/qloudalgo/

cp -R ${tmppath}/app/oryx-serving-als/target/standalone ${buildpath}/qloudalgo-serving-als/
cp -R ${tmppath}/app/oryx-serving-kmeans/target/standalone ${buildpath}/qloudalgo-serving-kmeans/
cp -R ${tmppath}/app/oryx-serving-rdf/target/standalone ${buildpath}/qloudalgo-serving-rdf/

# build 
curl -u "admin:Harbor12345" -X POST -H "Content-Type: application/json" -k "https://49.4.85.52/api/projects" -d '{"project_name":"'${project}'","public":1}'

 cd ${buildpath}/qloudalgo
 basetag_=${project}/qloudalgo:${ver}
 basetag="${project}\/qloudalgo:${ver}"
 docker build -t ${basetag_} .

 cd ${buildpath}/qloudalgo-als
 tag=reg.qloud.com/${project}/qloudalgo-als:${ver}
 sed -i "s/BASEIMAGE/${basetag}/g"  ${buildpath}/qloudalgo-als/Dockerfile
 docker build -t ${tag} .
 docker push ${tag}
 docker rmi ${tag}

 cd ${buildpath}/qloudalgo-kmeans
 tag=reg.qloud.com/${project}/qloudalgo-kmeans:${ver}
 sed -i "s/BASEIMAGE/${basetag}/g"  ${buildpath}/qloudalgo-kmeans/Dockerfile
 docker build -t ${tag} .
 docker push ${tag}
 docker rmi ${tag}
 
 cd ${buildpath}/qloudalgo-rdf
 tag=reg.qloud.com/${project}/qloudalgo-rdf:${ver}
 sed -i "s/BASEIMAGE/${basetag}/g"  ${buildpath}/qloudalgo-rdf/Dockerfile
 docker build -t ${tag} .
 docker push ${tag}
 docker rmi ${tag}

cd ${buildpath}/qloudalgo-serving-als
 tag=reg.qloud.com/${project}/qloudalgo-serving-als:${ver}
 sed -i "s/BASEIMAGE/${basetag}/g"  ${buildpath}/qloudalgo-serving-als/Dockerfile
 docker build -t ${tag} .
 docker push ${tag}
 docker rmi ${tag}
 
cd ${buildpath}/qloudalgo-serving-kmeans
 tag=reg.qloud.com/${project}/qloudalgo-serving-kmeans:${ver}
 sed -i "s/BASEIMAGE/${basetag}/g"  ${buildpath}/qloudalgo-serving-kmeans/Dockerfile
 docker build -t ${tag} .
 docker push ${tag}
 docker rmi ${tag}
 
cd ${buildpath}/qloudalgo-serving-rdf
 tag=reg.qloud.com/${project}/qloudalgo-serving-rdf:${ver}
 sed -i "s/BASEIMAGE/${basetag}/g"  ${buildpath}/qloudalgo-serving-rdf/Dockerfile
 docker build -t ${tag} .
 docker push ${tag}
 docker rmi ${tag}

 docker rmi ${basetag}

if [ ""$name != "" ]; then
   rm -R /tmp/$name
fi
