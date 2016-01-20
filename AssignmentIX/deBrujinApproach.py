#!/bin/python

import sys
import os

def kMinus1List(reads, k):
  kMinus1 = []

  for read in reads:
    i = 0
    while i+k < len(read):
      kMer = read[i:i+k]
      if kMer[:-1] not in kMinus1:
        kMinus1.append(kMer[:-1])
      if kMer[1:] not in kMinus1:
        kMinus1.append(kMer[1:])

  return kMinus1



if __name__ == '__main__':
  # check whether k was given or not
  if len(sys.argv) < 2:
    sys.exit('USAGE: %s <k>', % system.argv[0])

  try:
    k = int(sys.argv[1])
  except ValueError:
    sys.exit('k has to be a integer')

  
