#!/bin/python

import dbUtils

import os
import glob
import subprocess

#
#---------------------------------------------------------------------------------------------------------------------
#

DATA_PATH = '../data/'

GROUP1 = 'Wiesner/'

# type of the Blast database
BLAST_TYPE_NUCL = 'nucl'
BLAST_TYPE_PROT = 'prot'

#
#---------------------------------------------------------------------------------------------------------------------
#

# function to read in sequence file
def readSequence(path):
  # try to read sequence out of file
  seqFilePath = (glob.glob(path))
  if seqFilePath:
    seqFile = open(seqFilePath[0], 'r')
    seqFlag = False
    seq = ""
    for line in seqFile:
      if line and line.strip() != "":
        if seqFlag:
          seq += line.strip()
        if line[0] == ">":
          seqFlag = True
      else:
        break

    return seq

  else:
    return ""


# function to create table of protein construncts
def createProtConTable(db):
    # drop table if exists
    db.execute('drop table if exists proteinConstructs')
    # create table proteinConstructs
    db.execute('CREATE table proteinConstructs (id Integer Primary Key,name,author,date,box,position,project,organism,protein,domain,protein_family,mutation,backbone_vector,antibiotics,restriction,QC,RF,cloning_information,used_primer,constructed_from,concentration,DH5A_stock,Cplus_stock,notes,C_Term_Tag,N_Term_Tag,protein_sequence)')
    values = []
    protConFile = open(DATA_PATH + GROUP1 + 'proteinConstructs/proteinConstructs.csv','r')
    for line in protConFile.readlines():
        # just take non-empty lines into account
        if line:
            # skip all comment lines
            if line[0] == "#":
                continue
            lineSplit = line.split('|')
            seq = readSequence(DATA_PATH + GROUP1 + 'proteinConstructs/text_files/'+lineSplit[0]+'*')
            lineSplit.append(seq)
            values.append(tuple(lineSplit))

    # insert values into table
    db.execute('insert into proteinConstructs (id,name,author,date,box,position,project,organism,protein,domain,protein_family,mutation,backbone_vector,antibiotics,restriction,QC,RF,cloning_information,used_primer,constructed_from,concentration,DH5A_stock,Cplus_stock,notes,C_Term_Tag,N_Term_Tag,protein_sequence) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)', values)


# function to create table of cloning vectors
def createClonVecTable(db):
    # drop table, if it already exists
    db.execute('drop table if exists cloningVectors')
    # create table cloningVectors
    db.execute('create table cloningVectors (id Integer Primary Key,name,author,date,box,position,C_Term_Tags,N_Term_Tags,construct_cleavable,MCS,antibiotics,fw_seq_primer,rv_seq_primer,vector_map,size,concentration,DH5A_stock,notes,vector_sequence)')
    values = []
    clonVecFile = open(DATA_PATH + GROUP1 + 'cloningVectors/cloningVectors.csv', 'r')
    for line in clonVecFile.readlines():
        # just take non-empty lines into account
        if line:
            # skip all comment lines
            if line[0] == "#":
                continue
            lineSplit = line.split('|')
            seq = readSequence(DATA_PATH + GROUP1 + 'cloningVectors/text_files/'+lineSplit[0]+'*')
            lineSplit.append(seq)
            values.append(tuple(lineSplit))

    # insert values into table
    db.execute('insert into cloningVectors (id,name,author,date,box,position,C_Term_Tags,N_Term_Tags,construct_cleavable,MCS,antibiotics,fw_seq_primer,rv_seq_primer,vector_map,size,concentration,DH5A_stock,notes,vector_sequence) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)', values)


# function to create table of primer
def createPrimTable(db):
    # drop table, if already exists
    db.execute('drop table if exists primer')
    # create table primer
    db.execute('create table primer (id Integer Primary Key,name,author,date,box,position,project,designed_for,organism,protein,domain,restriction,QC,RF,cloning_information,direction,melting_temperature,concentration_uM,primer_sequence,protein_sequence,notes)')
    values = []
    primerFile = open(DATA_PATH + GROUP1 + 'primer/primer.csv', 'r')
    for line in primerFile.readlines():
        # just take non-empty lines into account
        if line:
            # skip all comment lines
            if line[0] == "#":
                continue
            lineSplit = line.split('|')
            values.append(tuple(lineSplit))

    # insert values into table
    db.execute('insert into primer (id,name,author,date,box,position,project,designed_for,organism,protein,domain,restriction,QC,RF,cloning_information,direction,melting_temperature,concentration_uM,primer_sequence,protein_sequence,notes) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)', values)


# function to create a fasta file with sequences from a database
def makeFastaFile(db,filename,group,table):
    # build query regarding selected table
    queryString = ""
    if table == "primer":
      queryString = "select id,name,primer_sequence from primer where primer_sequence is not \"\""
    elif table == "protConst":
      queryString = "select id,protein,protein_sequence from proteinConstructs where protein_sequence is not \"\""
    elif table == "clonVec":
      queryString = "select id,name,vector_sequence from cloningVectors where vector_sequence is not \"\""

    # select sequences
    db.select(queryString)
    results = db.fetchAll()
    f = open(group+filename, 'w')

    for result in results:
        f.write('>'+str(result[0])+'|'+result[1]+os.linesep)
        f.write(result[2]+os.linesep)

    f.close()
    

def makeBlastDB(dbType, fasta, group):
    # change to the blast db directory
    os.chdir(group)
    # call the executable of NCBI to make the blast db
    subprocess.call(["../makeblastdb", "-dbtype", dbType, "-in", fasta, "-parse_seqids"])
    # change back
    os.chdir('..')
    

#
#------------------------------------------------------------------------------------
#

if __name__ == '__main__':
    # get database handler
    db = dbUtils.DBUtil(GROUP1 + 'poodle_sqlite3.dat')
    
    # create tables
    print("building table of protein constructs...")
    createProtConTable(db)

    print("building table of cloning vectors...")
    createClonVecTable(db)

    print("building table of primer...")
    createPrimTable(db)

    print("successfully built the database")

    print("making fasta files for blast search...")
    primerDB = "primer.fasta"
    proteinDB = "protConst.fasta"
    vectorDB = "clonVec.fasta"
    makeFastaFile(db, primerDB, GROUP1, 'primer')
    makeFastaFile(db, proteinDB, GROUP1, 'protConst')
    makeFastaFile(db, vectorDB, GROUP1, 'clonVec')
    print("successfully made fasta files")

    print("building blast databases...")
    makeBlastDB(BLAST_TYPE_NUCL, primerDB, GROUP1)
    makeBlastDB(BLAST_TYPE_NUCL, proteinDB, GROUP1)
    makeBlastDB(BLAST_TYPE_NUCL, vectorDB, GROUP1)
    print("successfully built blast databases")
