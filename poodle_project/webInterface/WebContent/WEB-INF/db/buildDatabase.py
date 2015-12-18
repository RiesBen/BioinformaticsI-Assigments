#!/bin/python

import dbUtils
from Bio import SeqIO
from Bio.Seq import Seq
from Bio.SeqRecord import SeqRecord

import subprocess

#
#---------------------------------------------------------------------------------------------------------------------
#

DATA_PATH = '../data/'

#
#---------------------------------------------------------------------------------------------------------------------
#

# function to create table of protein construncts
def createProtConTable(db):
    # drop table if exists
    db.execute('drop table if exists proteinConstructs')
    # create table proteinConstructs
    db.execute('CREATE table proteinConstructs (id Primary Key,box,position,protein,domain,mutation,vector,antibiotics,cloning_sites,QC,RF,date,author,protein_family,sequenced,notes,concentration,DH5A_stock,Cplus_stock)')
    values = []
    protConFile = open(DATA_PATH + 'protein_constructs/protein_constructs.csv','r')
    for line in protConFile.readlines():
        # just take non-empty lines into account
        if line:
            # skip all comment lines
            if line[0] == "#":
                continue
            lineSplit = line.split('|')
            values.append(tuple(lineSplit))

    # insert values into table
    db.execute('insert into proteinConstructs (id,box,position,protein,domain,mutation,vector,antibiotics,cloning_sites,QC,RF,date,author,protein_family,sequenced,notes,concentration,DH5A_stock,Cplus_stock) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)', values)


# function to create table of cloning vectors
def createClonVecTable(db):
    # drop table, if it already exists
    db.execute('drop table if exists cloningVectors')
    # create table cloningVectors
    db.execute('create table cloningVectors (id Primary Key,box,position,name,size,tags,TEV_cleavage,MCS,antibiotics,fw_seq_primer,rv_seq_primer,vector_map,date,author,concentration,DH5A_stock,notes)')
    values = []
    clonVecFile = open(DATA_PATH + 'cloning_vectors/cloning_vectors.csv', 'r')
    for line in clonVecFile.readlines():
        # just take non-empty lines into account
        if line:
            # skip all comment lines
            if line[0] == "#":
                continue
            lineSplit = line.split('|')
            values.append(tuple(lineSplit))

    # insert values into table
    db.execute('insert into cloningVectors (id,box,position,name,size,tags,TEV_cleavage,MCS,antibiotics,fw_seq_primer,rv_seq_primer,vector_map,date,author,concentration,DH5A_stock,notes) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)', values)


# function to create table of primer
def createPrimTable(db):
    # drop table, if already exists
    db.execute('drop table if exists primer')
    # create table primer
    db.execute('create table primer (id Primary Key,box,position,name,protein,domain,restriction,QC,RF,restriction_site_or_mutation,primer_sequence,protein_sequence,notes,project,melting_temperature,Concentration_uM,date,author)')
    values = []
    primerFile = open(DATA_PATH + 'primer_database.csv', 'r')
    for line in primerFile.readlines():
        # just take non-empty lines into account
        if line:
            # skip all comment lines
            if line[0] == "#":
                continue
            lineSplit = line.split('|')
            values.append(tuple(lineSplit))

    # insert values into table
    db.execute('insert into primer (id,box,position,name,protein,domain,restriction,QC,RF,restriction_site_or_mutation,primer_sequence,protein_sequence,notes,project,melting_temperature,Concentration_uM,date,author) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)', values)


# function to create a fasta file with sequences from a database
def makeFastaFile(db,filename):
    # select primer sequences
    db.select("select id,name,primer_sequence from primer")
    results = db.fetchAll()
    records = []

    for result in results:
        rec = SeqRecord(Seq(result[2]),
                        id=result[0] + "|" + result[1])
        records.append(rec)

    # write sequences to file
    SeqIO.write(records, filename, "fasta")
    

def makeBlastDB(fasta):
    # call the executable of NCBI to make the blast db
    subprocess.call(["./makeblastdb", "-dbtype", "nucl", "-in", fasta])
    

#
#------------------------------------------------------------------------------------
#

if __name__ == '__main__':
    # get database handler
    db = dbUtils.DBUtil('poodle_sqlite3.dat')
    
    # create tables
    print "building table of protein constructs..."
    createProtConTable(db)

    print "building table of cloning vectors..."
    createClonVecTable(db)

    print "building table of primer..."
    createPrimTable(db)

    print "successfully built the database"

    print "making fasta file for blast search..."
    primerDB = "primer.fasta"
    makeFastaFile(db, primerDB)
    print "successfully made fasta file"

    print "building blast database..."
    makeBlastDB(primerDB)
    print "successfully built blast database"
