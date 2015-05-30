#!/bin/bash

cat >~/mongodb.repo <<EOL
[mongodb]
name=MongoDB Repository
baseurl=http://downloads-distro.mongodb.org/repo/redhat/os/x86_64/
gpgcheck=0
enabled=1
EOL

sudo mv ~/mongodb.repo /etc/yum.repos.d/mongodb.repo

sudo yum install -y mongo-10gen
