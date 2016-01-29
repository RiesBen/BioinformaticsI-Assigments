# This script calculates and plots the expected genetic distance differences 
# between Hamming distance and Jukes-Cantor correction
# author: Jonas Ditz

#!/bin/python

import matplotlib.pyplot as plt
import numpy as np

################################################################

SEQ_LENGTH = 200.0

################################################################

def calculateJC(distances):
  distJC = []
  for dist in distances:
    jc = -3./4. * np.log(1 - 4./3. * (dist/SEQ_LENGTH))
    distJC.append(jc)
  return distJC

def calculateHam(distances):
  distHam = []
  for dist in distances:
    distHam.append(dist/SEQ_LENGTH)
  return distHam

if __name__ == '__main__':
  t = range(150)
  ham = calculateHam(t)
  jc = calculateJC(t)

  plt.plot(t, ham, 'bs', label='observed distance p')
  plt.plot(t, jc, 'g^', label='expected genetic distance d')
  plt.xlabel('difference of two sequences')
  plt.ylabel('distance')
  plt.legend(loc='best')
  plt.show()
