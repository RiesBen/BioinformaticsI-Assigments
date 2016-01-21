#!/bin/python

import sys
import os

from graphviz import Digraph

def getkMere(reads, k):
  kMere = []

  for read in reads:
    print "work on Read "+read+"..."
    i = 0
    while i+k <= len(read):
      kMer = read[i:i+k]
      if kMer not in kMere:
        kMere.append(kMer)
      i = i + 1

  print "all computed k mere:"
  print kMere
  return kMere


def drawDeBruijn(kMere, k):
  deBruijn = Digraph(comment='de Bruijn graph for given reads', format='png')
  deBruijn.graph_attr['rankdir'] = 'LR'
  
  # generate k-1 mere
  kMinus1 = []
  for mer in kMere:
    if mer[:-1] not in kMinus1:
      kMinus1.append(mer[:-1])
    if mer[1:] not in kMinus1:
      kMinus1.append(mer[1:])

  # insert nodes
  for mer in kMinus1:
    deBruijn.node(mer, mer)
  
  # insert edges
  for mer1 in kMinus1:
    for mer2 in kMinus1:
      if mer1 == mer2:
        continue
      for kMer in kMere:
        if ((mer1 == kMer[:-1]) and (mer2 == kMer[1:])):
          deBruijn.edge(mer1,mer2)
 
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
  kMere = getkMere(reads, k)
  drawDeBruijn(kMere, k)
