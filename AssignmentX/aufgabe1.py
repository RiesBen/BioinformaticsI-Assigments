#!/bin/python

import graphviz as gv

if __name__ == '__main__':
  tree = gv.Digraph(comment='overlap', format='png')
  nodes = [['A','1s'],['B','1e'],['C','2s'],['D','2e'],
           ['E','3s'],['F','3e'],['G','4s'],['H','4e'],
           ['I','5s'],['J','5e'],['K','6s'],['L','6e'],
           ['M','7s'],['N','7e'],['O','8s'],['P','8e']]
  edges = [['E','D','-230'],['L','D','-150'],['I','P','-150'],['L','P','-80'],
           ['O','M','-400'],['L','F','-420'],['H','D','-40'],['H','J','-250'],
           ['H','A','-400'],['B','J','-350'],['I','M','-50'],['N','K','-180'],
           ['N','F','-100']]

  for i in range(0,8):
    auxTree = gv.Digraph(comment='auxTree')
    auxTree.graph_attr['rankdir'] = 'LR'
    auxTree.node(nodes[2*i][0], nodes[2*i][1])
    auxTree.node(nodes[2*i+1][0], nodes[2*i+1][1])
    auxTree.edge(nodes[2*i][0], nodes[2*i+1][0])
    tree.subgraph(auxTree)

  #tree.graph_attr['rankdir'] = 'LR'

  for edge in edges:
    tree.edge(edge[0], edge[1], label=edge[2], dir='none')

  tree.render(filename='overlapGraph')
