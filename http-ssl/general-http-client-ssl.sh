#!/bin/bash

CURRENT_DIR=$(cd $(dirname $0);pwd)
cd ${CURRENT_DIR}

common_name=message-handler

#The directory to save keystore
DIR_NAME=$common_name
cd $DIR_NAME

#server keystore alias
server_alias_name=$common_name-server
#server keystore name
server_keystore_name=$common_name-server
#client keystore alias
client_alias_name=$common_name-client
#client keystore name
client_keystore_name=$common_name-client
#client dns name
dns_name=client
#password
password=123456
#expiration date
validity_date=3650

#genaral client keystore
keytool -genkey -alias $client_alias_name -keyalg RSA -keysize 2048 -sigalg SHA256withRSA -keystore $client_keystore_name.jks -dname "CN=$dns_name" -validity $validity_date -storepass $password -keypass $password
#export client cert
keytool -export -alias $client_alias_name -file $client_keystore_name.cer -keystore $client_keystore_name.jks -storepass $password -keypass $password
#import client cert to server
keytool -import -alias $client_alias_name -file $client_keystore_name.cer -keystore $server_keystore_name.jks -storepass $password -keypass $password -noprompt
#import server cert to client
keytool -import -alias $server_alias_name -file $server_keystore_name.cer -keystore $client_keystore_name.jks -storepass $password -keypass $password -noprompt
#transfer jks to p12
keytool -importkeystore -srckeystore $client_keystore_name.jks -destkeystore $client_keystore_name.p12 -srcstoretype JKS -deststoretype PKCS12 -srcstorepass $password -deststorepass $password -srckeypass $password -destkeypass $password -srcalias $client_keystore_name -destalias $client_keystore_name -noprompt