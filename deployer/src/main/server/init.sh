#!/bin/sh

export SERVER_HOME=$(cd `dirname $0`; pwd)
export QBIT_CONF_FILE="$SERVER_HOME/qloudbus.conf"
export DATA_SOURCE_FILE="$SERVER_HOME/dataSource.properties"
export SYS_CONFIGURE="$SERVER_HOME/configure.properties"
export MARKET_SERVICE_CONF="$SERVER_HOME/market-service.conf"
#cat > $DATA_SOURCE_FILE  <<EOF
#	        poolclass=druid
#          	driverClassName=com.mysql.jdbc.Driver
#          	url=jdbc:mysql://${DB_URL}/${DB_NAME}?autoReconnect=true&characterEncoding=UTF-8
#          	username=${DB_USER}
#          	password=${DB_PWD}
#          	initialSize=1
#          	minIdle=1
#         	maxActive=5
#EOF

cat > $MARKET_SERVICE_CONF  <<EOF
{

   "qloudConsul": false,  // disable or enable consul
   "consulService": "http://192.168.56.103:8500/v1/agent/service/helloworld", // consul service
   "rootURI": "",
   "host": "${MARKET_SERVICE_IP}",   // address
   "port": ${MARKET_SERVICE_PORT},          // port
   "protocol": "ws",      // http | ws
   "ssl": false,
   "batchSize": "100",
   "flushInterval": "20"

}
EOF

cat > $SYS_CONFIGURE  <<EOF

 marketAgent.chartMuseum.sourceUrl=${MA_CM_SURL}
 marketAgent.chartMuseum.targetUrl=${MA_CM_TGURL}
 marketAgent.registry.CATA_LOGS=${MA_RE_CATALOG}
 marketAgent.registry.BASE_MARKET_RE_URL=${MA_RE_MKRE_URL}
 marketAgent.registry.BASE_BANK_PASS_RE_URL=${MA_RE_BKRE_URL}
 marketAgent.rancher.CATALOGNAME=${CATALOGNAME}
 marketAgent.rancher.VERSION=${RANVERSION}
 marketAgent.rancher.TOKEN=${RANTOKEN}
 marketAgent.rancher.USERNAME=${RANUSERNAME}
 marketAgent.rancher.CSRF=${RANCSRF}
 marketAgent.rancher.PROJECTS.URL=https://${RANADDRESS}/v3/projects
 marketAgent.rancher.CREATNAMESPACE.URL=https://${RANADDRESS}/v3/clusters/{{clusterID}}/namespace
 marketAgent.rancher.CHECKAPP.URL=https://${RANADDRESS}/v3/templateVersions/{{appName}}?{{rancherVersion}}
 marketAgent.rancher.LAUNCHAPP.URL=https://${RANADDRESS}/v3/projects/{{projectID}}/app
 marketAgent.rancher.REFRESHCATALOG.URL=https://${RANADDRESS}/v3/catalogs?action=refresh
 marketAgent.rancher.PROJECT.NAME=${PROJECTNAME}
 marketAgent.common.IPADDRESS=${COMIPADDRESS}
 marketAgent.common.CUSTOMERID=${COMCUSTOMERID}
 marketAgent.common.COMPANY=${COMCOMPANY}
 marketAgent.common.TOKEN=${COMTOKEN}
 marketAgent.common.LicensePATH=${LicensePATH}
 marketAgent.common.userName=${USERNAME}
 marketAgent.common.PASSWORD=${PASSWORD}
 marketAgent.auth.BASEURL=${AUTHPATH}
 marketAgent.common.MARKETADDRESS=${MARKETADDRESS}
 marketAgent.rancher.NAMESPACE=${NAMESPACE}
 marketAgent.VERSION=${VersionCode}
 marketAgent.upload.SIZE=${UpLoadBlockSize}
 marketAgent.VOLUME=${vlum}

EOF



