From ubuntu:latest

RUN apt update && apt list --upgradable && apt install tree git vim -y
RUN mkdir /home/DevOps-git
WORKDIR /home/DevOps-git
RUN git clone https://github.com/HaeyoonJo/DevOps-git.git
