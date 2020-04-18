# Run Docker private registry and app server on AWS EC2

- server IP( example ip addr ):
  docker-registry: 52.59.225.211
  docker: 3.120.159.80

### 1. server config or install packages

- Update and isntall packages

run_script.sh
```
#!/bin/bash
sudo apt-get update && sudo apt-get upgrade -y
sudo wget -qO- https://get.docker.io/ | sh && sudo usermod -aG docker $USER
# docker-compose installation
sudo curl -L "https://github.com/docker/compose/releases/download/1.25.4/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
docker-compose --version

$ chmod +x run_script.sh
$ ./run_script.sh
```

- re-login into the server

### 1. Docker registry server

- config docker registry server
```
$ sudo service docker stop
$ docker -d --insecure-registry localhost:5000
$ sudo vi /etc/init.d/docker
  ...
  DOCKER_OPTS=--insecure-registry localhost:5000
  ...
$ sudo service docker restart
```

- pull docker registry image
```
$ docker pull registry:latest
$ docker run -d \
  -p 5000:5000 \
  --name docker-regiestry \
  -v /tmp/registry:/var/lib/registry \
  --restart=always \
  registry:latest
```

- If you're using registry storage on AWS S3 bucket, you can follow the command below:
```
$ docker run -d \
    -p 5000:5000 \
    --name docker-registry \
    -v /tmp/registry:/var/lib/registry \
    --restart=always \
    -e "SETTING_FLAVOR=s3" \
    -e "AWS_BUCKET=docker-registry-bucket" \
    -e "REGISTRY_STORAGE_S3_REGION=eu-central-1" \
    -e "STORAGE_PATH=/docker-registry" \
    -e "AWS_KEY={ACCESS_KEY}" \
    -e "AWS_SECRET={SECRET_KEY}" \
    registry:latest
```

### 2. App server

- Config app server to push and pull docker image to private Docker registry server
```
$ sudo vi /etc/docker/daemon.json
{
  "insecure-registries":["52.59.225.211:5000"]
}
```

- Run [Dockerfile](Dockerfile), build image and check the container is running based on configured Nginx image
```
$ docker build -t nginx/haeyoon:0.1
$ docker run -d -p 80:80 \
    --name=nginx_server \
    -v /root/data:/data \
    nginx/haeyoon:0.1
```
 - push created nginx/haeyoon:0.1 image to private docker registry.
 ```
 $ docker tag nginx/haeyoon:0.1 52.59.225.211:5000/nginx/haeyoon:0.1
 $ docker push 52.59.225.211:5000/nginx/haeyoon:0.1
 ```

 - Move on registry server checking the image pushed the path following:
      /tmp/registry/docker/registry/v2/repositories/

e.g., the directory should look like below:
```
ubuntu@ip-172-31-36-192:~$ ls /tmp/registry/docker/registry/v2/repositories/
mysql  nginx

ubuntu@ip-172-31-36-229:~$ tree /tmp/registry/docker/registry/v2/blobs/sha256/
/tmp/registry/docker/registry/v2/blobs/sha256/
├── 0e
│   └── 0e34effa47d122bb05a1d9cb3f3b037bc10681bf651fe0ce5b875b7cf1d1af68
│       └── data
├── 11
│   └── 111ba0647b87721c5700e04da65c5adde40fe7791e8374fd8813e3639636562d
│       └── data
```
**ENJOY DOCKER**
