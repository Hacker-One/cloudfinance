


@echo off

if not defined SERVER_HOME goto set_server_home

:cont
if not defined JAVA_HOME goto no_java_home

set QBIT_CONF_FILE=qloudbus.conf

"%JAVA_HOME%\bin\java" -Xms512M -Xmx512M -cp ".;%SERVER_HOME%\classes;%SERVER_HOME%\lib\*;%SERVER_HOME%\*;%SERVER_HOME%\extra\*" com.qloudfin.qloudbus.sdk.server.QloudServiceServer %1

goto end

:no_java_home
echo ERROR: Set JAVA_HOME to the path where the J2SE 1.8 is installed
goto end 

:set_server_home
set SERVER_HOME=%~dp0
goto cont

:end
