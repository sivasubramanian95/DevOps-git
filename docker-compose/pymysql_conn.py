import mysql.connector
from mysql.connector import errorcode
from datetime import date, datetime, timedelta

cnx = mysql.connector.connect(
        host='db',
        user='user',
        password='passwd',
        database='sample'
    )

cur = cnx.cursor()
now = datetime.now().date()

add_example = ("INSERT INTO sample_table (id, INS_DATE, NAME, VALUE) VALUE(11, " + now + ", 'example-11', 'value-11');")

cur.execute(add_example)
cnx.commit()
version = cur.fetchone()
cur.close()
cnx.close()
print("Database version: {}".format(version[0]))
