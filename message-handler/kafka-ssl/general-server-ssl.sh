#!/bin/bash

CURRENT_DIR=$(cd $(dirname $0);pwd)
cd ${CURRENT_DIR}

rm -rf general-ssl-jks
mkdir general-ssl-jks
cd general-ssl-jks

name=localhost
password=123456
#服务端生成密钥库和证书
keytool -genkey -keystore kafka.server.keystore.jks -validity 365 -storepass $password -keypass $password -keyalg RSA -dname "CN=$name" -storetype pkcs12
keytool -keystore kafka.server.keystore.jks -certreq -file cert-file -storepass $password -keypass $password

#配置CA，生成密钥和证书
openssl req -new -newkey rsa:4096 -days 365 -x509 -subj "/CN=$name" -keyout ca-key -out ca-cert -nodes
#用CA的密钥和证书对服务端证书签名
openssl x509 -req -CA ca-cert -CAkey ca-key -in cert-file -out cert-signed -days 365 -CAcreateserial -passin pass:$password

#导入CA证书和签过名的服务端证书，并将CA证书加入truststore中
keytool -keystore kafka.server.keystore.jks -alias CARoot -import -file ca-cert -storepass $password -keypass $password -noprompt
keytool -keystore kafka.server.keystore.jks -import -file cert-signed -storepass $password -keypass $password -noprompt
keytool -keystore kafka.server.truststore.jks -alias CARoot -import -file ca-cert -storepass $password -keypass $password -noprompt






