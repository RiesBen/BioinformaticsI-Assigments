#!/bin/python

import sys
import os
from itertools import groupby


# function with analyse a given psl file
def analysePSL(inputFile, numberOfReads):
    mappedReads = []

    # open psl file and read-in all the mapped reads
    inFile = open(inputFile, 'r')
    for line in inFile:
        # skip empty lines
        cleanedLine = line.strip()
        if cleanedLine:
            splitLine = cleanedLine.split()
            # we are interested in lines, which start with "psl:"
            if splitLine[0] == "psl:":
                mappedReads.append(splitLine[10])

    # get just the unique entries in mappedReads
    allMappedReads = set(mappedReads)
    uniqueMappedReads = [k for k, group in groupby(mappedReads) if len(list(group)) == 1]

    # print statistics
    print "number of all mapped reads: "
    print "    absolut number: ", len(allMappedReads)
    print "    percentage: ", len(allMappedReads)/numberOfReads * 100
    print "number of unique mapped reads: "
    print "    aboslut number:", len(uniqueMappedReads)
    print "    percentage: ", len(uniqueMappedReads)/float(numberOfReads) * 100


if __name__ == '__main__':
    # check, whether there are enough commandline parameters
    if len(sys.argv) < 3:
        print "USAGE: python analysis.py <FILENAME> <NUMBER OF READS>"
        sys.exit()
  
    inputFile = sys.argv[1]
    numberOfReads = int(sys.argv[2])
 
    #start analysis
    analysePSL(inputFile, numberOfReads)
