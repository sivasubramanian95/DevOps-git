From ubuntu:latest
MAINTAINER HaeyoonJo <test@gmail.com>

RUN apt update && apt list --upgradable
RUN apt install tree git vim -y
RUN mkdir /home/DevOps-git

WORKDIR /home/DevOps-git

CMD ['/bin/df', '-h']

RUN git clone https://github.com/HaeyoonJo/DevOps-git.git
