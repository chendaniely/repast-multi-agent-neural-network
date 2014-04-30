# -*- coding: utf-8 -*-
"""
Created on Mon Apr 28 20:31:29 2014

@author: dchen
"""
import os
import sys
import getopt
from collections import defaultdict


def main(argv):
    os.path.dirname(os.path.realpath(__file__))
    print 'Number of arguments:', len(sys.argv), 'arguments.'
    print 'Argument List:', str(sys.argv)
    print 'first argument', str(sys.argv[0])
    rCSV = str(sys.argv[1])
    print 'parameter', file

    edges = defaultdict(list)
    with open(rCSV) as csv:
        next(csv)
        for line in csv:
            print line
            elements = line.strip().split(',')
            key, value = elements[1:3]
            print key
            print value
            edges[key].append(value)
    print edges

    dan_edge_list = 'dan_' + rCSV
    with open(dan_edge_list, 'w+') as f:
        for key in edges:
            links = ""
            for link in edges[key][:-1]:
                links = links + str(link) + ','
            links += str(edges[key][-1])
            output = ','.join([key, links])
            output += '\n'
            print output
            f.write(output)

if __name__ == "__main__":
    main(sys.argv[1:])
