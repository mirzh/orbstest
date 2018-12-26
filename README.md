# Orbstest

## General design
In this implementation The game is represented by turns. Each turn is receiving a board (matrix(n*n) ) in its constructor
and it is representing the turn's state.

Representing the game by turns will make it possible to track the entire game progress and enable Undo/Redo features in the future.

The board size is configured to be 4X4 but it can be any size (2X2, 3X3, 5X5 etc..)
(In the unit tests I used a 2X2 board since it is easier to test...)

The random generation of the board is happening outside and provided to the first Turn object. 
The Turn object is deterministic and therefore it is easy to test.

## How to run
I used sbt as build tool.
The main class of the project is at:
```
src/main/scala/com/orbstest/Game.scala
```
