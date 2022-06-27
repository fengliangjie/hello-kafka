#!/bin/bash

CURRENT_DIR=$(cd $(dirname $0);pwd)
cd ${CURRENT_DIR}

cd general-ssl-jks

name=siemens_001
password=123456

rm -rf $name
mkdir $name

keytool -genkey -keystore $name/$name.client.keystore.jks -validity 3650 -storepass $password -keypass $password -keyalg RSA -dname "CN=$name" -storetype pkcs12
keytool -keystore $name/$name.client.keystore.jks -certreq -file $name/client-cert-sign-request -storepass $password -keypass $password
openssl x509 -req -CA ca-cert -CAkey ca-key -in $name/client-cert-sign-request -out $name/client-signed-cert -days 3650 -CAcreateserial -passin pass:$password

keytool -keystore $name/$name.client.truststore.jks -alias CARoot -import -file ca-cert -storepass $password -keypass $password -noprompt
keytool -keystore $name/$name.client.keystore.jks -alias CARoot -import -file ca-cert -storepass $password -keypass $password -noprompt
keytool -keystore $name/$name.client.keystore.jks -import -file $name/client-signed-cert -storepass $password -keypass $password -noprompt
