Docker Registry 오류내용 및 노트 정리


# ERROR
  [ ]ERROR 1. Docker Warning
  ```
  apt install tree git vim -y
  WARNING: apt does not have a stable CLI interface. Use with caution in scripts.
  ```


  [v] ERROR 2. git branch checkout했을 때, 경로가 없다 생기는 오류
  - staging 브랜치에서 디렉토리와 파일을 생성해놓고, feature 브랜치로 체크아웃 했을 때 경로가 없어서 해당 오류가 발생했었다.
  ```
  $ git checkout -t origin/feature
  Branch 'feature' set up to track remote branch 'feature' from 'origin'.
  Switched to a new branch 'feature'

  $ git branch
  fatal: Unable to read current working directory: No such file or directory
  ```

  [v] ERROR 3.
  ```
  $ docker pull 35.158.251.167:5000/hello:0.1
  Error response from daemon: Get https://35.158.251.167:5000/v2/: http: server gave HTTP response to HTTPS client
  ```
  SOLVED: https://www.leafcats.com/200
  이미지를 pull받는 서버 측에 docker설정에서 daemon.json파일에 insecure-registries를 추가해야 한다.
  파일경로 및 파일내용: /etc/docker/daemon.json
  {
      "insecure-registries": ["192.168.56.1:5000"] # Docker registry IP
  }

  [ ] ERROR 3-1.
  EC2에서 도커 레지스트리를 만들고 push 한 후, 맥북 로컬에서 이미지를 가져오려고 했는데 ERROR 3과 같은 오류가 발생함.

  [ ] ERROR 4.
  Error response from daemon: conflict: unable to delete f4dcc4e6b185 (must be forced) - image is referenced in multiple repositories

# QUESTIONS
  [ ] question 1
    docker container에서 패키치 설치하고 이미지로 커밋하면, 설치된 패키지( 변경내용 ) 내용은 이미지 변경에서 보는건가? 아니면, docker diff로 보는건가?

  [ ] question 2
    Docker registry에서 이미지 받을 때, rename 가능한가?
      마찬가지로, Dockerhub에서 받아도 안되는데, 이게 될까?
    >> 해결책: 이미지를 받은 다음, docker tag 명령어를 사용하여 변경하면 되긴하다

  [ ] question 3
    docker rmi 이미지를 삭제할 때, untagged vs deleted 차이점은?
    ```
    ubuntu@ip-172-31-37-164:~$ docker rmi 3.121.40.255:5001/hello:0.2
    Untagged: 3.121.40.255:5001/hello:0.2
    Untagged: 3.121.40.255:5001/hello@sha256:5decab0f8eed43f1a9d6df51a1aa2c0f850d09b966dd23327e69e839275ae59d

    ubuntu@ip-172-31-37-164:~$ docker images
    REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
    hello               0.2                 3f49195ef3c5        4 hours ago         156MB
    ubuntu              latest              72300a873c2c        3 weeks ago         64.2MB

    ubuntu@ip-172-31-37-164:~$ docker rmi hello:0.2
    Untagged: hello:0.2
    Deleted: sha256:3f49195ef3c5a241386ce5870f4415aaf9dacd346326ecacddac097c4bdfab67
    ```

  [ ] question 4
    AWS EC2 AMI 생성시, 인스턴스 커넥션이 끊어지는가?

  [ ] question 5
    도커 이미지를 레지스트리로 등록할 때, ip addr가 로컬호스트면 커넥션이 거절된다.. 오류인가 원래 커넥션이 거절되나?
    ```
    $ docker tag img:tag localhost:5000/img:tag
    $ docker push localhost:5000/img:tag
    ```
  [ ] question 6
    EC2 서버 생성마다, 도커 레지스트리 sg그룹에 ip를 추가해줘야 한다. 이거 자동화 어케함?

  [ ] question 7
    도커 레지스트리 및 nginx서버 프로비저닝은 IAC로 가능한가? terraform, ansible 등

# Note.
    1. git remote branch update
    	- remote  를 업데이트 함으로써, 모든 브랜치를 가져온다.

    Git branch -r: 원격 리모트 브랜치 확인

    Git branch -a: 원격, 로컬 모든 저장소 브랜치 확인

    위의 상황에서 만약 원격 저장소의 feature/create-meeting branch를 가져오고 싶다면, $ git checkout -t origin/feature/create-meeting 처럼 하면 된다.
    -t 옵션과 원격 저장소의 branch 이름을 입력하면 로컬의 동일한 이름의 branch를 생성하면서 해당 branch로 checkout을 한다.
    만약 branch 이름을 변경하여 가져오고 싶다면 $ git checkout -b [생성할 branch 이름] [원격 저장소의 branch 이름] 처럼 사용하면 된다.
 
    2. Docker image 빌드하면, base image도 같이 받아지나보다??
    ubuntu@ip-172-31-47-246:~/example/docker$ docker images
    REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
    hello               0.1                 57ca9777c4c0        18 seconds ago      156MB
    ubuntu              latest              72300a873c2c        3 weeks ago         64.2MB

    원래 docker images하면 아무것도 없었음.




    3. How to sync written file ( docker file etc) with local, remote server


    4. WORKDIR가 /etc/nginx 였다. 도커파일 참고
