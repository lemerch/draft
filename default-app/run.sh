#!/bin/bash

sudo docker build -t test .
sudo docker run -p 8080:8080 -p 8090:8090 test