#!/bin/python

import dbUtils

import os
import subprocess

#
#---------------------------------------------------------------------------------------------------------------------
#

DATA_PATH = '../data/'

GROUP1 = 'Wiesner/'

#
#---------------------------------------------------------------------------------------------------------------------
#

# function to create table of protein construncts
def createProtConTable(db):
    # drop table if exists
    db.execute('drop table if exists proteinConstructs')
    # create table proteinConstructs
    db.execute('CREATE table proteinConstructs (id Primary Key,box,position,protein,domain,protein_family,mutation,backbone_vector,antibiotics,cloning_sites,QC,RF,used_primer,constructed_from,concentration,DH5A_stock,Cplus_stock,project,date,author,notes)')
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
    db.execute('insert into proteinConstructs (id,box,position,protein,domain,protein_family,mutation,backbone_vector,antibiotics,cloning_sites,QC,RF,used_primer,constructed_from,concentration,DH5A_stock,Cplus_stock,project,date,author,notes) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)', values)


# function to create table of cloning vectors
def createClonVecTable(db):
    # drop table, if it already exists
    db.execute('drop table if exists cloningVectors')
    # create table cloningVectors
    db.execute('create table cloningVectors (id Primary Key,box,position,name,size,C_Term_Tags,N_Term_Tags,construct_cleavable,MCS,antibiotics,fw_seq_primer,rv_seq_primer,vector_map,concentration,DH5A_stock,date,author,notes)')
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
    db.execute('insert into cloningVectors (id,box,position,name,size,C_Term_Tags,N_Term_Tags,construct_cleavable,MCS,antibiotics,fw_seq_primer,rv_seq_primer,vector_map,concentration,DH5A_stock,date,author,notes) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)', values)


# function to create table of primer
def createPrimTable(db):
    # drop table, if already exists
    db.execute('drop table if exists primer')
    # create table primer
    db.execute('create table primer (id Primary Key,box,position,name,direction,organism,protein,domain,restriction,QC,RF,restriction_site_or_mutation,melting_temperature,Concentration_uM,primer_sequence,protein_sequence,project,designed_for,date,author,notes)')
    values = []
    primerFile = open(DATA_PATH + 'primer/primer_database.csv', 'r')
    for line in primerFile.readlines():
        # just take non-empty lines into account
        if line:
            # skip all comment lines
            if line[0] == "#":
                continue
            lineSplit = line.split('|')
            values.append(tuple(lineSplit))

    # insert values into table
    db.execute('insert into primer (id,box,position,name,direction,organism,protein,domain,restriction,QC,RF,restriction_site_or_mutation,melting_temperature,Concentration_uM,primer_sequence,protein_sequence,project,designed_for,date,author,notes) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)', values)


# function to create a fasta file with sequences from a database
def makeFastaFile(db,filename,group):
    # select primer sequences
    db.select("select id,name,primer_sequence from primer")
    results = db.fetchAll()
    f = open(group+filename, 'w')

    for result in results:
        f.write('>'+result[0]+'|'+result[1]+os.linesep)
        f.write(result[2]+os.linesep)

    f.close()
    

def makeBlastDB(fasta, group):
    # change to the blast db directory
    os.chdir(group)
    # call the executable of NCBI to make the blast db
    subprocess.call(["../makeblastdb", "-dbtype", "nucl", "-in", fasta, "-parse_seqids"])
    # change back
    os.chdir('..')
    

#
#------------------------------------------------------------------------------------
#

if __name__ == '__main__':
    # get database handler
    db = dbUtils.DBUtil(GROUP1 + 'poodle_sqlite3.dat')
    
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
    makeFastaFile(db, primerDB, GROUP1)
    print "successfully made fasta file"

    print "building blast database..."
    makeBlastDB(primerDB, GROUP1)
    print "successfully built blast database"
