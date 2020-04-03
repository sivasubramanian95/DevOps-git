import urllib
import pymysql
from urllib.request import urlopen

db = pymysql.connect(host='db', port='3306', user='haeyoon', passwd='1234', db='example_db', charset='utf8', autocommit=True)
cursor = db.cursor()
#cursor.execute("select version()")
cursor.execute("insert into example_db (id, INS_DATE, NAME, VALUE) values(11, now(), 'example-11', 'value-11');")
#data = cursor.fetchone()
#print("------------------- db version: %s " % data)
db.close()
