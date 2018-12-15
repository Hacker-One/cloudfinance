#!/bin/sh

export SERVER_HOME=$(cd `dirname $0`; pwd)
export QBIT_CONF_FILE="$SERVER_HOME/qloudbus.conf"
export DATA_SOURCE_FILE="$SERVER_HOME/dataSource.properties"
export MARKET_AGENT_CONF="$SERVER_HOME/market-agent.conf"
export PURCHASEVENT_CONSUMER="$SERVER_HOME/consumer.properties"
export SERVER_CONFIG_FILE="$SERVER_HOME/configure.properties"

cat > $DATA_SOURCE_FILE  <<EOF

	        poolclass=druid
          	driverClassName=com.mysql.jdbc.Driver
          	url=jdbc:mysql://${DB_URL}/${DB_NAME}?autoReconnect=true&characterEncoding=UTF-8
          	username=${DB_USER}
          	password=${DB_PWD}
          	initialSize=1
          	minIdle=1
          	maxActive=5

EOF

cat > $SERVER_CONFIG_FILE  <<EOF
            # chart museum address
	        chartAddress=${CHART_ADDRESS}
	        # api gateway address
            api_gateway_address=${APIGATEWAY_ADDRESS}
            # api auth address
            authBaseAddress=${AUTH_ADDRESS}
            # redishost
            redis_host=${REDIS_HOST}
            #redis port
            redis_port=${REDIS_PORT}
            # redis timeout
            redis_timeout=${REDIS_TIMEOUT}
            # mart trial area enviroment address
            sample_enviroment_deployer_address=${EVN_TRY_DEP_ADR}
            redis_uri=${REDIS_URI}
EOF



cat > $MARKET_AGENT_CONF  <<EOF
{

   "qloudConsul": false,  // disable or enable consul
   "consulService": "http://192.168.56.103:8500/v1/agent/service/helloworld", // consul service
   "rootURI": "",
   "host": "${MARKET_AGENT_IP}",   // address
   "port": ${MARKET_AGENT_PORT},          // port
   "protocol": "ws",      // http | ws
   "ssl": false,
   "batchSize": "100",
   "flushInterval": "20"

}
EOF

cat > $PURCHASEVENT_CONSUMER  <<EOF

  enable.auto.commit=false
  bus.poll.inteval=10
  max.poll.records=10000
  value.deserializer=org.apache.kafka.common.serialization.StringDeserializer
  auto.offset.reset=latest
  bus.seek = end
  bootstrap.servers= ${PURCHASE_KAFKA_ADDR}
  bus.timeout.inteval=30000
  key.deserializer=org.apache.kafka.common.serialization.StringDeserializer
  bus.commit.inteval = 1
  bus.exception=EVENT.Qloudbus.Exception

EOF
