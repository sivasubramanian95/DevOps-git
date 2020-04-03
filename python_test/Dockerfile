FROM python:alpine

ADD python_check_mariadb_version.py /

RUN pip install -U pip

RUN pip install PyMySQL

CMD [ "python", "-u", "./python_check_mariadb_version.py" ]
