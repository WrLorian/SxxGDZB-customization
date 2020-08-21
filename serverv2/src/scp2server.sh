#!/bin/bash

echo "获取最新版本"

a=$(ls target/customization-*.jar|awk 'END {print}')
echo $a
echo "停止"
ssh xin@HTA_UBUNTU "cd /home/xin/ydaq_server&&./stop.sh"
scp src/main/resources/application.properties xin@HTA_UBUNTU:/home/xin/ydaq_server
scp $a xin@HTA_UBUNTU:/home/xin/ydaq_server
echo "启动"
ssh xin@HTA_UBUNTU "cd /home/xin/ydaq_server&&./start.sh > /dev/null"
exit

#a=$(ls target/customization-*.jar|awk 'END {print}')
#echo $a
##scp src/main/resources/application.properties xin@HTA_UBUNTU:/home/xin/ydaq_server/test
#scp $a xin@HTA_UBUNTU:/home/xin/ydaq_server/test
##ssh xin@HTA_UBUNTU "cd /home/xin/ydaq_server/test&&./run.sh > /dev/null"
#exit

