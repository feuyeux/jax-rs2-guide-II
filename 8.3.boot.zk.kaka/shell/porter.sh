#!/usr/bin/env bash
BASE=/Users/erichan/sourcecode/feuyeux/jax-rs2-guide-II/7.3.boot.zookeeper/shell
port=8080
for (( ; ${port} <= 8100;  port++  ))
do
  status=$(curl -s -w %{http_code} -o /dev/null :${port}/hi2)
  echo "status=${status}"
  if [ ${status} == 200 ]; then
    break
  fi
done
if [ ${status} == 200 ]; then
  echo "port=${port}"
  cp ${BASE}/nginx_template.conf ${BASE}/nginx_services.conf
  services=$(curl :${port}/hi2)
  echo "services=${services}"
  sed -i -e "s/MICRO_SERVICES/${services}/g" ${BASE}/nginx_services.conf
  /usr/local/bin/nginx -t -c ${BASE}/nginx_services.conf
  /usr/local/bin/nginx -s reload -c ${BASE}/nginx_services.conf
  echo "Done"
else
  echo "No available services"
fi