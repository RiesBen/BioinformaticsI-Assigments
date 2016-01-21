#!/bin/python

import sys
import os

from graphviz import Digraph

def kMinus1List(reads, k):
  kMinus1 = []

  for read in reads:
    print "work on Read "+read+"..."
    i = 0
    while i+k <= len(read):
      kMer = read[i:i+k]
      if kMer[:-1] not in kMinus1:
        kMinus1.append(kMer[:-1])
      if kMer[1:] not in kMinus1:
        kMinus1.append(kMer[1:])
      i = i + 1

  print "all computed k-1 mere:"
  print kMinus1
  return kMinus1


def drawDeBruijn(kMinus1, k):
  deBruijn = Digraph(comment='de Bruijn graph for given reads', format='png')
  deBruijn.graph_attr['rankdir'] = 'LR'
  
  # insert nodes
  for mer in kMinus1:
    deBruijn.node(mer, mer)
  
  # insert edges
  for mer1 in kMinus1:
    for mer2 in kMinus1:
      if mer1 == mer2:
        continue
      if mer1[1:] == mer2[:-1]:
        deBruijn.edge(mer1, mer2)  
 
  # print graph
  deBruijn.render(filename="deBruijnGraph_"+str(k)+"mere")


if __name__ == '__main__':
  # check whether k was given or not
  if len(sys.argv) < 2:
    sys.exit("USAGE: "+sys.argv[0]+" <k>")

  try:
    k = int(sys.argv[1])
  except ValueError:
    sys.exit('k has to be a integer')

  reads = ['ACCGT', 'CGTAACGTT', 'ACGTTA', 'GTTAA', 'TAAACTG']
  kMinus1Mere = kMinus1List(reads, k)
  drawDeBruijn(kMinus1Mere, k)
