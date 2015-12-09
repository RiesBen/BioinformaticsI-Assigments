#!/bin/python

from graphviz import Digraph


def drawTree(nodes, edges, fname):
  tree = Digraph(comment='suffix tree', format='png')
  for node in nodes:
    tree.node(node[0], node[1])
  for edge in edges:
    tree.edge(edge[0], edge[1], label=edge[2])
  tree.render(filename=fname)


if __name__ == '__main__':
  # draw all steps for naive suffix tree constuction of text=CTAGTAGCAG

  # step 1 'C'
  drawTree([['A',''],['B','']], [['A','B','CTAGTAGCAG$']], 'tree_step01')

  # step 2 'T'
  drawTree([['A',''],['B',''],['C','']], [['A','B','CTAGTAGCAG$'],['A','C','TAGTAGCAG$']], 'tree_step02')

  # step 3 'A'
  drawTree([['A',''],['B',''],['C',''],['D','']], 
           [['A','B','CTAGTAGCAG$'],['A','C','TAGTAGCAG$'], ['A','D','AGTAGCAG$']], 'tree_step03')

  # step 4 'G'
  drawTree([['A',''],['B',''],['C',''],['D',''],['E','']], 
           [['A','B','CTAGTAGCAG$'],['A','C','TAGTAGCAG$'], ['A','D','AGTAGCAG$'],['A','E','GTAGCAG$']], 'tree_step04')

  # step 5 'T'
  drawTree([['A',''],['B',''],['C',''],['D',''],['E',''],['F',''],['G','']],
           [['A','B','CTAGTAGCAG$'],['A','C','TAG'], ['A','D','AGTAGCAG$'],['A','E','GTAGCAG$'],
            ['C','F','TAGCAG$'],['C','G','CAG$']], 
           'tree_step05')

  # step 6 'A'
  drawTree([['A',''],['B',''],['C',''],['D',''],['E',''],['F',''],['G',''],['H',''],['I','']],
           [['A','B','CTAGTAGCAG$'],['A','C','TAG'], ['A','D','AG'],['A','E','GTAGCAG$'],
            ['C','F','TAGCAG$'],['C','G','CAG$'],['D','H','TAGCAG$'],['D','I','CAG$']],
           'tree_step06')

  # step 7 'G'
  drawTree([['A',''],['B',''],['C',''],['D',''],['E',''],['F',''],['G',''],['H',''],['I',''],
            ['J',''],['K','']],
           [['A','B','CTAGTAGCAG$'],['A','C','TAG'], ['A','D','AG'],['A','E','G'],
            ['C','F','TAGCAG$'],['C','G','CAG$'],['D','H','TAGCAG$'],['D','I','CAG$'],
            ['E','J','TAGCAG$'],['E','K','CAG$']],
           'tree_step07')

  # step 8 'C'
  drawTree([['A',''],['B',''],['C',''],['D',''],['E',''],['F',''],['G',''],['H',''],['I',''],
            ['J',''],['K',''],['L',''],['M','']],
           [['A','B','C'],['A','C','TAG'], ['A','D','AG'],['A','E','G'],
            ['C','F','TAGCAG$'],['C','G','CAG$'],['D','H','TAGCAG$'],['D','I','CAG$'],
            ['E','J','TAGCAG$'],['E','K','CAG$'],['B','L','TAGTAGCAG$'],['B','M','AG$']],
           'tree_step08')

  # step 9 'A'
  drawTree([['A',''],['B',''],['C',''],['D',''],['E',''],['F',''],['G',''],['H',''],['I',''],
            ['J',''],['K',''],['L',''],['M',''],['N','']],
           [['A','B','C'],['A','C','TAG'], ['A','D','AG'],['A','E','G'],
            ['C','F','TAGCAG$'],['C','G','CAG$'],['D','H','TAGCAG$'],['D','I','CAG$'],
            ['E','J','TAGCAG$'],['E','K','CAG$'],['B','L','TAGTAGCAG$'],['B','M','AG$'],
            ['D','N','$']],
           'tree_step09')

  #step 10 'G'
  drawTree([['A',''],['B',''],['C',''],['D',''],['E',''],['F',''],['G',''],['H',''],['I',''],
            ['J',''],['K',''],['L',''],['M',''],['N',''],['O','']],
           [['A','B','C'],['A','C','TAG'], ['A','D','AG'],['A','E','G'],
            ['C','F','TAGCAG$'],['C','G','CAG$'],['D','H','TAGCAG$'],['D','I','CAG$'],
            ['E','J','TAGCAG$'],['E','K','CAG$'],['B','L','TAGTAGCAG$'],['B','M','AG$'],
            ['D','N','$'],['E','O','$']],
           'tree_step10')

  # step 11 '$'
  drawTree([['A',''],['B',''],['C',''],['D',''],['E',''],['F','1'],['G','2'],['H','3'],['I','4'],
            ['J','5'],['K','6'],['L','7'],['M','8'],['N','9'],['O','10'],['P','11']],
           [['A','B','C'],['A','C','TAG'], ['A','D','AG'],['A','E','G'],
            ['C','F','TAGCAG$'],['C','G','CAG$'],['D','H','TAGCAG$'],['D','I','CAG$'],
            ['E','J','TAGCAG$'],['E','K','CAG$'],['B','L','TAGTAGCAG$'],['B','M','AG$'],
            ['D','N','$'],['E','O','$'],['A','P','$']],
           'tree_step11')
