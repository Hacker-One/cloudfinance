#!/usr/bin/env bash

NEXUS_VERSION=3.14.0-04
NEXUS_DIR=/opt/sonatype/nexus
MARKET_CONF_FILE=$NEXUS_DIR/etc/qloudmarket.properties
HELM_CONF_FILE=$NEXUS_DIR/system/org/sonatype/nexus/assemblies/nexus-core-feature/$NEXUS_VERSION/nexus-core-feature-$NEXUS_VERSION-features.xml

#
# helm
#
sed -i 's@nexus-repository-maven</feature>@nexus-repository-maven</feature>\n        <feature prerequisite="false" dependency="false">nexus-repository-helm</feature>@g' $HELM_CONF_FILE
sed -i 's@<feature name="nexus-repository-maven"@<feature name="nexus-repository-helm" description="org.sonatype.nexus.plugins:nexus-repository-helm" version="0.0.6">\n        <details>org.sonatype.nexus.plugins:nexus-repository-helm</details>\n        <bundle>mvn:org.sonatype.nexus.plugins/nexus-repository-helm/0.0.6</bundle>\n    </feature>\n    <feature name="nexus-repository-maven"@g' $HELM_CONF_FILE

#
# auth
#
cat > $MARKET_CONF_FILE <<EOF
#
# mock test
#
faked=${ENABLE_FAKED:-false}

#
# oauth authentication
#
authUrl=http://${QLOUD_AUTHC_ADDRESS:-qloudgrid:8080}/security/users

#
# oauth authorization
#
# path=<repository>/<image>
#
# {
#   "status": true
# }
#
permissionUrl=http://${QLOUD_AUTHZ_ADDRESS:-marketservice:8090}/permissions

EOF
