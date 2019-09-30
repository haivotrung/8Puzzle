# 8Puzzle
This program will:
•	Create a random (valid) 8-puzzle
•	Determine if the created puzzle is solvable – not all are.  See https://www.geeksforgeeks.org/check-instance-8-puzzle-solvable/ for a formula to determine whether any given puzzle is solvable
•	If not, it should print the puzzle, then a message
•	If so, it should solve the puzzle, and print out the initial state and all states on the way to the goal state, in order, including the goal state

• breadth-first search
• depth-first search
• weighted-cost search
• A* where the evaluation function is equal to the sum of moves required to get to the current state plus the number of misplaced tiles 
• A* where the evaluation function is equal to the sum of moves required to get to the current state plus the Manhattan distance to the goal (h2 
• Also works for 15 puzzle

Notes

Weighted-cost works as follows – the cost of getting to a state is equal to the cost of getting to its parent, plus the value on the tile that is swapped with the blank to get to this state.  The lowest cost state is the one chosen to expand.

The association between searches and heuristics is:
•	Depth-first and breadth-first search use no heuristic
•	Weighted-cost uses the cost required to get to this state (if there is a tie, pick randomly)
•	One version of A* uses number of moves + misplaced tiles
•	The other version of A* uses number of moves + Manhattan distance (number of swaps)

You may find it useful to check/avoid the following conditions:
•	Expanding a node that has already been expanded
•	Placing a node on the frontier that is already on the frontier or has previously been expanded
Failure to avoid these conditions might lead to an infinite search.

You may find the discussion on https://www.cs.princeton.edu/courses/archive/fall15/cos226/assignments/8puzzle.html to be useful, in particular its outline of how to determine if a board in unsolvable.  It also has some nice examples.

No solution to the 8-puzzle (necessarily) has more than 31 moves.  No solution to the 15-puzzle (necessarily) has more than 81 moves.  Any solution longer than this has a repeated state in it somewhere.  You may find that your solution has (many) more.  That is fine.  If your solution has more than 10 moves, just print out:
•	The first five states
•	Ellipsis ( … )
•	The last five states
•	The total number of steps required


Example output:
0 4 5 //
2 1 7 //
6 8 3 //

Unsolvable. Generating new Board...

5 4 8 //
6 3 0 //
1 7 2 //

Unsolvable. Generating new Board...

2 6 8 //
3 7 5 //
0 1 4 //

Manhattan A Star Search Initializing

Moves made: 159
Time elapsed: 0 milliseconds

2 6 8 //
3 7 5 //
0 1 4 //

2 6 8 //
3 7 5 //
1 0 4 //

2 6 8 //
3 0 5 //
1 7 4 //

2 6 8 //
0 3 5 //
1 7 4 //

2 6 8 //
1 3 5 //
0 7 4 //

...
0 2 3 //
1 4 6 //
7 5 8 //

1 2 3 //
0 4 6 //
7 5 8 //

1 2 3 //
4 0 6 //
7 5 8 //

1 2 3 //
4 5 6 //
7 0 8 //

1 2 3 //
4 5 6 //
7 8 0 //

Moves to goal: 20
