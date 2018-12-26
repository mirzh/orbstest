# Orbstest

## General design
In this implementation The game is represented by turns. Each turn is receiving a board (matrix(n*n) ) in its constructor
and it is representing the turn's state.

Representing the game by turns will make it possible to track the entire game progress and enable Undo/Redo features in the future.

The random generation of the board is happening outside and provided to the first Turn object. 
The Turn object is deterministic and therefore it is easy to test.

## How to run
I used sbt as build tool.
The main class of the project is at:
```
src/main/scala/com/orbstest/Game.scala
```
