rubik-cube
==========

Implementation of Iterative Deepening A* with 3 heuristics:

* IDA1: basically no heuristic
* IDA2: for each cube we calculate the minimum number of moves to get it to the correct position, then take the maximum of all these values
* IDA3: implementation of 3 pattern databases as described in "Finding Optimal Solutions to Rubik's Cube Using Pattern Databases" by Richard E. Korf.
