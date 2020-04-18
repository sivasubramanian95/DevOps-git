FROM python:alpine

RUN pip install -U pip

RUN pip install mysql-connector-python

RUN apk update && apk add bash

WORKDIR /home/pytest

ADD pymysql_conn.py .

ADD wait-for-it.sh .

RUN chmod +x wait-for-it.sh
