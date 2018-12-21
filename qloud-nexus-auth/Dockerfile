FROM sonatype/nexus3:3.14.0
USER root

#
#RUN mkdir -p /opt/sonatype/nexus/system/com/qloudfin/nexus-qloudauth-plugin/1.0.0/
#

#
# helm
#
COPY ./target/nexus-repository-helm-0.0.6.jar /opt/sonatype/nexus/system/org/sonatype/nexus/plugins/nexus-repository-helm/0.0.6/

#
# auth
#
COPY ./target/nexus-qloudauth-plugin-1.0.0.jar /opt/sonatype/nexus/system/com/qloudfin/nexus-qloudauth-plugin/1.0.0/
COPY ./target/feature/feature.xml /opt/sonatype/nexus/system/com/qloudfin/nexus-qloudauth-plugin/1.0.0/nexus-qloudauth-plugin-1.0.0-features.xml
COPY ./pom.xml /opt/sonatype/nexus/system/com/qloudfin/nexus-qloudauth-plugin/1.0.0/nexus-qloudauth-plugin-1.0.0.pom

#
# global
#
COPY ./logback.xml /opt/sonatype/nexus/etc/logback
COPY ./init.sh /opt/sonatype

RUN echo "mvn\:com.qloudfin/nexus-qloudauth-plugin/1.0.0 = 200" >> /opt/sonatype/nexus/etc/karaf/startup.properties \
 && chmod +x /opt/sonatype/init.sh \
 && sh /opt/sonatype/init.sh
