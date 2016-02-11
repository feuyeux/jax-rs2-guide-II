#!/usr/bin/env bash
cp nginx_template.conf nginx_services.conf
port=8080
status=$(curl -s -w %{http_code} -o /dev/null :${port}/hi2)
while [ ${status} != 200 ]
do
  (( port++ ))
  status=$(curl -s -w %{http_code} -o /dev/null :${port}/hi2)
done
services=$(http :${port}/hi2)
echo "current services :${services}"
sed -i -e "s/MICRO_SERVICES/${services}/g" nginx_services.conf
sudo nginx -t -c ${PWD}/nginx_services.conf
sudo nginx -s reload