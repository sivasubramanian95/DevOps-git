FROM python:alpine

RUN pip install -U pip

RUN pip install mysql-connector-python

WORKDIR /

RUN echo hello world
RUN pwd

ADD pymysql_conn.py /

RUN echo hello pythonmysql
RUN pwd

CMD ["python", "pymysql_conn.py"]
