#!/bin/bash

CURRENT_DIR=$(cd $(dirname $0);pwd)
cd ${CURRENT_DIR}

common_name=configuration-service

#The directory to save keystore
DIR_NAME=$common_name
rm -rf $DIR_NAME
mkdir $DIR_NAME
cd $DIR_NAME

#server keystore alias
server_alias_name=$common_name-server
#server keystore name
server_keystore_name=$common_name-server
#server dns name
dns_name=localhost
#password
password=123456
#expiration date
validity_date=3650

keytool -genkey -alias $server_alias_name -keyalg RSA -keysize 2048 -sigalg SHA256withRSA -keystore $server_keystore_name.jks -dname "CN=$dns_name" -validity $validity_date -storepass $password -keypass $password
keytool -export -alias $server_alias_name -file $server_keystore_name.cer -keystore $server_keystore_name.jks -storepass $password -keypass $password


