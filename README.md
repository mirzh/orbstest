# orbstest

In this implementation The game is represented by turns. Each turn is receiving a board (matrix(n*n) ) in its constructor
and it is representing the turn's state.

The random generation of the board is happening outside and provided to the first Turn object. 
The Turn object is deterministic and therefore it is easy to test.

