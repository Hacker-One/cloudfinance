#!/bin/sh

export SERVER_HOME=$(cd `dirname $0`; pwd)
export SYS_CONFIGURE="$SERVER_HOME/configure.properties"


cat > $SYS_CONFIGURE  <<EOF

	 marketAddress=${MART_ADDRESS}

EOF
