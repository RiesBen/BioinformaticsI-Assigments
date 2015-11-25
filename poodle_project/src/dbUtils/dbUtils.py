#!/bin/python

import sqlite3


class DBUtil:
    # Constuctor
    def __init__(self, dbName):
        self.conn = sqlite3.connect(dbName)
        self.cursor = self.conn.cursor()

    # Destructor
    def __del__(self):
        self.conn.commit()
        self.conn.close()

    # Execute a arbitrary query.
    # This function is mainly for create, insert and delete statements.
    # For SELECT statements use select().
    # @param	query	query to execute
    # @param	values	array of tuples
    # @return	none
    def execute(self, query, values = None):
        if (values == None):
            self.cursor.execute(query)
            return
        self.cursor.executemany(query, values)

    # Execute a SELECT statement
    # @param	query	query to execute
    # @param	values	tuple of values
    # @return	none
    def select(self, query, values=None):
        if values == None:
            self.cursor.execute(query)
            return
        self.cursor.execute(query, values)

    # Fetch next row
    # @return	next row of result set
    def fetch(self):
        return self.cursor.fetchone()

    # Fetch all (remaining) rows of a query result
    # @return	all rows of result set
    def fetchAll(self):
        return self.cursor.fetchall()
