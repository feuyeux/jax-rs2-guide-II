#!/usr/bin/env bash
port=18080
for (( ; ${port} <= 18100;  port++  ))
do
  status=$(curl -s -w %{http_code} -o /dev/null :${port}/hi2)
  echo "status=${status}"
  if [ ${status} == 200 ]; then
    break
  fi
done
if [ ${status} == 200 ]; then
  echo "port=${port}"
  cp nginx_template.conf nginx_services.conf
  services=$(curl :${port}/hi2)
  echo "services=${services}"
  sed -i -e "s/MICRO_SERVICES/${services}/g" nginx_services.conf
  sudo docker exec -ti nginx1 nginx -s reload
  echo "Done"
else
  echo "No available services"
fi