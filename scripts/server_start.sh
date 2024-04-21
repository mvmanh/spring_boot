#!/bin/bash
cd /home/ec2-user/spring_boot
# nohup java -jar app.jar &
sudo java -jar -Dserver.port=80 \
    *.jar > /dev/null 2> /dev/null < /dev/null &
