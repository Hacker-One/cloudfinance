#!/bin/sh

export SERVER_HOME=$(cd `dirname /opt/sonatype/nexus/etc/`; pwd)
export AUTH_CONF_FILE="$SERVER_HOME/qloudmarket.properties"

cat > $AUTH_CONF_FILE  <<EOF
             faked=${IS_FAKE}
             authUrl=${AUTH_URL}
             permissionUrl=${PERMISSION_URL}

EOF