# Docker-compose Note

1. Note

도커 컴포즈를 이용하여, 웹서버-데이터베이스 연동 테스트 진행

2. Summary

python - mysql5.7 버전 연동

테스트환경: AWS EC2 docker설치하여 진행

python mysql connection을 이용하였으며, 테스트 데이터(mysql-init.sql)는 깃허브에서 가져옴.

3. 오류

mysql:latest 이미지를 이용하였다가 mysql서버가 웹서버올라오기전에 셧다운되는 현상 발생.

[github/docker-library](https://github.com/docker-library/mysql/issues/352) 이슈에서 찾아보니 8.x 버전에서 이슈가 있었음.

아래의 내용은 그에 대한 내용

** [here link](https://github.com/docker-library/mysql/issues/407)

```
It seems that 'latest' used to point to 5.x, but 3 days ago it changed to '8.x' as reported here, and "mbind: Operation not permitted" messages started appearing, in my case whenever trying to create a database from another container.
My quick workaround was to use 5.7.22 just as @johncmunson did.
Any pointer on what changed between versions that lead to this behavior?
```
4. 결과적으로 디비에 11번 데이터 입력되는 것 확인 완료

5. 추가로 개발테스트 진행 고려사항

- 클러스터링

- 도커 컴포즈 로그 관리

- 웹/디비 서버 볼륨

- 보안
