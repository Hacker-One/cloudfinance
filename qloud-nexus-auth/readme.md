curl -X POST \
  'http://localhost:8081/service/rest/v1/components?repository=maven-releases' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -H 'X-Market-Token: eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6IlJBWVE6TVRYSzpWQzQzOjRCVzU6Nk9PUjpWSkZVOlpKVkM6V1NHWjpaR1FMOlVJVFY6N0dTUDpNSUY3In0.eyJpc3MiOiJBdXRoIFNlcnZpY2UiLCJzdWIiOiJjaGVueGlhb2ppIiwiYXVkIjoiRG9ja2VyIHJlZ2lzdHJ5IiwiZXhwIjoxNTQxOTk0Njc4LCJuYmYiOjE1NDE5OTM3NjgsImlhdCI6MTU0MTk5Mzc3OCwianRpIjoiMTE3NDUxMjkwNzQ0MzYyNTQ3IiwiYWNjZXNzIjpbeyJ0eXBlIjoicmVwb3NpdG9yeSIsIm5hbWUiOiJxbG91ZGRvcC9qZW5raW5zIiwiYWN0aW9ucyI6W119XX0.oIt2KUHG859vw0BwcVK89Tt4UU7m_juGHoNx5QFPVm36PjJawDyPiuDTPQX9JcVTGN_aqcZ2lwsfFNfTSb6-3bMfK8dxDtoUmAPkDtE4lLKrI4m8UbJtczUcRhSTh9drzRTw80hcTlIVTdhsSih8nqn3Pfz4TwldZ26GIRDl8dLtstTtE_dOBmfJkY4JKTpKVFAb9sRYqluydoR8LKqhzjlMUoeRDoxjyUJwMT6oRPHGytKhGOIgKY2Urt5oecyduwrmfFh45ZqjoaKKuw9SSpkiEEu7tyzgCw8I3OstZKva7GnH6H5j3VNJMm4Wkr2eivwXXBk8xR_gOdaYOs_YNA' \
  -H 'cache-control: no-cache' \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -F maven2.groupId=com.qloudfin \
  -F maven2.artifactId=qloudbus-bridge \
  -F maven2.version=1.0.0 \
  -F 'maven2.asset1=@F:\downloads\qloudbus-bridge-1.0.0.jar' \
  -F maven2.asset1.extension=jar
  <br>
<br>
curl -X GET \
  'http://49.4.95.119:8081/service/rest/v1/search/assets/download?group=com.qloudfin&name=qloudbus-bridge&version=1.0.0&maven.extension=jar' \
  -H 'Postman-Token: f65cb69f-1fe8-40f9-b89b-828919af19f8' \
  -H 'X-Market-Token: eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6IlJBWVE6TVRYSzpWQzQzOjRCVzU6Nk9PUjpWSkZVOlpKVkM6V1NHWjpaR1FMOlVJVFY6N0dTUDpNSUY3In0.eyJpc3MiOiJBdXRoIFNlcnZpY2UiLCJzdWIiOiJjaGVueGlhb2ppIiwiYXVkIjoiRG9ja2VyIHJlZ2lzdHJ5IiwiZXhwIjoxNTQyMDc2MDcyLCJuYmYiOjE1NDIwNzUxNjIsImlhdCI6MTU0MjA3NTE3MiwianRpIjoiNTY3MDc3MjYzMTM2NjU2ODQ5IiwiYWNjZXNzIjpbeyJ0eXBlIjoicmVwb3NpdG9yeSIsIm5hbWUiOiJxbG91ZGRvcC9qZW5raW5zIiwiYWN0aW9ucyI6WyJwdWxsIl19XX0.EDD1U6ejHWLMJe9zB7l3MeOEHiHxL9HLCEItiZ3OalHyWXmXKOFQq5B3xIFIHRW1ap4_StXs-1inIBKe5ceAyHEvgu8qBHGqkSwCgBwunbXaJwh0cyDHivg8B8IoYrBoNrrun9B5LXtW_CJoey3YHQt3gnEvybDfZAVW65KlnX3WY9tjaVC7B8LliBueuv3LqE2eNzzaPSGmKnOjtucPJ6UdIVH_I0cZU6nKi53Qe7BoNgUgIVoY7R0DDYPqV3UymdItVDWjaoAiQvjVqXIvB3IDnFtI8lSS8yu8DjRAqx3BjKch3eI9MXpXZPrQcqTuMmxx7fk99rSzy--G-rfKgg' \
  -H 'cache-control: no-cache'

<br>
[setup]
mkdir -p /opt/sonatype/nexus/system/com/qloudfin/                                                  <br>
unzip /opt/sonatype/nexus/system/com/qloudfin/nexus-qloudauth-plugin.zip -d /opt/sonatype/nexus/system/com/qloudfin/            <br>
echo "mvn\:com.qloudfin/nexus-qloudauth-plugin/1.0.0 = 200" >> /opt/sonatype/nexus/etc/karaf/startup.properties                   <br>
cp truststore                                                                                            <br>
/opt/sonatype/nexus/etc
cat > /opt/sonatype/nexus/etc/qloudmarket.properties <<EOF                                                                         <br>
keystorename=truststore                                                                   <br>
keystorepasswd=qloud@2018                                                                  <br>

EOF                                             <br>