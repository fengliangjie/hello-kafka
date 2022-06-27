#!/bin/bash

CURRENT_DIR=$(cd $(dirname $0);pwd)
cd ${CURRENT_DIR}

DIR_NAME=configuration-service
rm -rf $DIR_NAME
mkdir $DIR_NAME
cd $DIR_NAME

key_alias_name=configuration-service
key_configuration_name=configuration-service
dns_name=localhost
password=123456
validity_date=3650
keytool -genkey -alias $key_alias_name -keystore $key_configuration_name.jks -dname "CN=$dns_name" -validity $validity_date -storepass $password -keypass $password -keyalg RSA -keysize 2048 -sigalg SHA256withRSA