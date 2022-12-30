# -*- coding: utf-8 -*-
"""hillClimbing.py
  Authors : Changmin Lee, Yasmine Sid, Isabel Leon
"""
# Q4
import random
import numpy as np
import time


def from_file(filename, sep='\n'):
  "Parse a file into a list of strings, separated by sep."
  return open(filename).read().strip().split(sep)


def makeGrid(e):
  # When you realize youre not smart to do with for loops...
  # This function convert a string to a list of square lists.
  grid = list()
  grid.append([e[0], e[1], e[2], e[9], e[10], e[11], e[18], e[19], e[20]])
  grid.append([e[3], e[4], e[5], e[12], e[13], e[14], e[21], e[22], e[23]])
  grid.append([e[6], e[7], e[8], e[15], e[16], e[17], e[24], e[25], e[26]])
  grid.append([e[27], e[28], e[29], e[36], e[37], e[38], e[45], e[46], e[47]])
  grid.append([e[30], e[31], e[32], e[39], e[40], e[41], e[48], e[49], e[50]])
  grid.append([e[33], e[34], e[35], e[42], e[43], e[44], e[51], e[52], e[53]])
  grid.append([e[54], e[55], e[56], e[63], e[64], e[65], e[72], e[73], e[74]])
  grid.append([e[57], e[58], e[59], e[66], e[67], e[68], e[75], e[76], e[77]])
  grid.append([e[60], e[61], e[62], e[69], e[70], e[71], e[78], e[79], e[80]])

  return grid


def fillSquare(square):
  # This function fills one square which has 0s
  unassigned = notAssignedList(square)
  j = 0
  for (i, nb) in enumerate(square):
    if nb == '0':
      square[i] = unassigned[j]
      j += 1

  return unassigned


def notAssignedList(squareIndex):
  # This function determines the values to be assigned to empty space,
  # from its given square and shuffle it in random order.
  li = ['1', '2', '3', '4', '5', '6', '7', '8', '9']

  for i in squareIndex:
    if i != '0':
      li.remove(i)
  random.shuffle(li)
  return li


def fillAllSquares(grid):
  # This function fills all the squares with not-existing random numbers.
  # Returns square grid and unassigned values list.
  res = grid
  li = []
  for s in res:
    ua = fillSquare(s)
    li.append(ua)

  return res, li


def changeValue(row, c1, c2):
  # This Function takes a square, exchange 2 values' position.
  res = row
  c1pos = -1
  c2pos = -1
  for i, c in enumerate(res):
    if c == c1:
      c1pos = i

  for i, c in enumerate(res):
    if c == c2:
      c2pos = i

  if (c1pos != -1 and c2pos != -1):
    temp = res[c1pos]
    res[c1pos] = res[c2pos]
    res[c2pos] = temp

  return res


def transform(e):
  # This converts a list of squares to the list of rows
  # When you find out you're not smart again :(
  li = []
  sli = []
  for i in range(3):
    for j in range(3):
      sli.append(e[i][j])

  li.append(sli)

  sli = []
  for i in range(3):
    for j in range(3):
      sli.append(e[i][j + 3])
  li.append(sli)

  sli = []
  for i in range(3):
    for j in range(3):
      sli.append(e[i][j + 6])
  li.append(sli)

  sli = []
  for i in range(3):
    for j in range(3):
      sli.append(e[i + 1][j])
  li.append(sli)

  sli = []
  for i in range(3):
    for j in range(3):
      sli.append(e[i + 1][j + 3])
  li.append(sli)

  sli = []
  for i in range(3):
    for j in range(3):
      sli.append(e[i + 1][j + 6])
  li.append(sli)

  sli = []
  for i in range(3):
    for j in range(3):
      sli.append(e[i + 2][j])
  li.append(sli)

  sli = []
  for i in range(3):
    for j in range(3):
      sli.append(e[i + 2][j + 3])
  li.append(sli)

  sli = []
  for i in range(3):
    for j in range(3):
      sli.append(e[i + 2][j + 6])
  li.append(sli)

  return li


def check(e):
  # This counts the number of conflicts which occurs in a list of rows
  conflicts = 0
  for i in e:
    conflicts += rowCheck(i)

  for j in np.array(e).transpose():
    conflicts += rowCheck(j)

  return conflicts


def rowCheck(row):
  # Check conflicts within a row.
  res = 0
  l = '123456789'
  for i in l:
    res += countCollision(i, row)

  return res


def countCollision(car, li):
  # Counts conflicts of one character in a row
  count = -1
  for i in li:
    if i == car:
      count += 1

  if count <= 0:
    return 0
  return count


def hillClimbingSearching(e):
  # From sudoku in string form, convert to square grid form
  # print it and perform hill climbing until there is no improvements
  # print the final result, even if it doesn't give correct answer.
  grid = makeGrid(e)
  print(transform(grid))
  squareGrid, changeableValues = fillAllSquares(grid)

  rowOrientedGrid = transform(squareGrid)

  currentConflicts = check(rowOrientedGrid)

  for (i, row) in enumerate(squareGrid):
    rowWithChangeableValues = changeableValues[i]
    for j in rowWithChangeableValues:
      for k in rowWithChangeableValues:
        if j != k:
          changedSGrid = squareGrid
          changedSGrid[i] = changeValue(row, j, k)
          conflict = check(transform(changedSGrid))
          if currentConflicts > conflict:

            if conflict == 0:
              print(conflict)

            squareGrid = changedSGrid
            currentConflicts = conflict

  print(transform(squareGrid))


def runAll(file):
  times = []
  file_list = from_file(file, sep='\n')
  for f in file_list:
    start = time.time()
    hillClimbingSearching(f)
    t = time.time() - start
    times.append(t)

  moyen = np.mean(times)
  maxim = np.max(times)

  print(moyen, maxim)


if __name__ == '__main__':
  runAll("100sudoku.txt")