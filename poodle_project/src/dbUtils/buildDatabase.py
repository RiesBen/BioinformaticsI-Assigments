#!/bin/python

import dbUtils


# function to create table of protein construncts
def createProtConTable(db):
    # drop table if exists
    db.execute('drop table if exists proteinConstructs')
    # create table proteinConstructs
    db.execute('CREATE table proteinConstructs (id Primary Key,box,position,protein,domain,mutation,vector,additional_number,AB,cloning_sites_QC_RF,additional_Cloning_site_behind_protein,date,author,type_of_enzyme,sequence,notes,concentration,DH5A_stock,Cplus_stock)')
    values = []
    protConFile = open('../../data/protein_constructs/protein_constructs.csv','r')
    for line in protConFile.readlines():
        # just take non-empty lines into account
        if line:
            # skip all comment lines
            if line[0] == "#":
                continue
            lineSplit = line.split('|')
            values.append(tuple(lineSplit))

    # insert values into table
    db.execute('insert into proteinConstructs (id,box,position,protein,domain,mutation,vector,additional_number,AB,cloning_sites_QC_RF,additional_Cloning_site_behind_protein,date,author,type_of_enzyme,sequence,notes,concentration,DH5A_stock,Cplus_stock) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)', values)


# function to create table of cloning vectors
def createClonVecTable(db):
    # drop table, if it already exists
    db.execute('drop table if exists cloningVectors')
    # create table cloningVectors
    db.execute('create table cloningVectors (Id Primary Key,Box,position,vector,size_bp,tags,TEV_cleavage,MCS,cloning_site,cloning_site_behind_protein,AB,fw_seq_primer,rv_seq_primer,protein,vector_map,date,author,concentration,DH5A_stock,notes)')
    values = []
    clonVecFile = open('../../data/cloning_vectors.csv', 'r')
    for line in clonVecFile.readlines():
        # just take non-empty lines into account
        if line:
            # skip all comment lines
            if line[0] == "#":
                continue
            lineSplit = line.split('|')
            values.append(tuple(lineSplit))

    # insert values into table
    db.execute('insert into cloningVectors (Id,Box,position,vector,size_bp,tags,TEV_cleavage,MCS,cloning_site,cloning_site_behind_protein,AB,fw_seq_primer,rv_seq_primer,protein,vector_map,date,author,concentration,DH5A_stock,notes) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)', values)


# function to create table of primer
def createPrimTable(db):
    # drop table, if already exists
    db.execute('drop table if exists primer')
    # create table primer
    db.execute('create table primer (Id Primary Key,box,position,primer_name,protein,domain,restriction_QC_or_RF,restriction_site_or_mutation,primer_sequence,protein_sequence,notes_additional_informations_specification,primer_group,melting_temperature,Concentration_uM,date,name)')
    values = []
    primerFile = open('../../data/primer_database.csv', 'r')
    for line in primerFile.readlines():
        # just take non-empty lines into account
        if line:
            # skip all comment lines
            if line[0] == "#":
                continue
            lineSplit = line.split('|')
            values.append(tuple(lineSplit))

    # insert values into table
    db.execute('insert into primer (Id,box,position,primer_name,protein,domain,restriction_QC_or_RF,restriction_site_or_mutation,primer_sequence,protein_sequence,notes_additional_informations_specification,primer_group,melting_temperature,Concentration_uM,date,name) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)', values)



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
