FROM node:8.12
# Create app directory
RUN mkdir -p /usr/src/app
# Bundle app source
ADD QloudMart.tgz /usr/src/app
WORKDIR /usr/src/app/qloudmart
ENV NODE_ENV dev
CMD ["/usr/src/app/qloudmart/start.sh"]
EXPOSE 8080
# Build image
# docker build -t qloud_market:v1 .
#image save
#docker save 3ebf7fbd3040  -o ~/work/thirdCode/QloudMarket/QloudMarket.tar
#docker images|grep none|awk '{print "docker rmi -f " $3}'|sh
# docker rm -f $(docker ps -q -a)
#tar zcvf QloudMart.tgz qloudmart
# Run docker
# docker run -e SYSTEMCONFIG='{"port":"8080","url":"http://49.4.93.173:32090"}' -p 8080:8080  qloud_market:v1
#数据格式 http://localhost:8080/api/products/seed
#{
#  "port":"8080",
#  "url":"http://49.4.93.173:32090"
#}
