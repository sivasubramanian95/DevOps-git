FROM python:alpine

ADD python_mariadb_conn.py /

RUN pip install -U pip

RUN pip install PyMySQL

CMD [ "python", "-u", "./python_mariadb_conn.py" ]
