#!/bin/bash
echo 'run after_install.sh: ' >> /home/ec2-user/spring_boot/deploy.log

echo 'cd /home/ec2-user/spring_boot' >> /home/ec2-user/spring_boot/deploy.log
cd /home/ec2-user/spring_boot >> /home/ec2-user/spring_boot/deploy.log

# echo 'npm install' >> /home/ec2-user/spring_boot/deploy.log 
# npm install >> /home/ec2-user/spring_boot/deploy.log