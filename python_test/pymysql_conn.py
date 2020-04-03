import pymysql

con = pymysql.connect('localhost', 'user', 'password', 'sample')

with con:

    cur = con.cursor()
    cur.execute("insert into sample_table (id, INS_DATE, NAME, VALUE) values(11, now(), 'example-11', 'value-11');")
    cur.execute("SELECT VERSION()")
    version = cur.fetchone()

    print("Database version: {}".format(version[0]))
