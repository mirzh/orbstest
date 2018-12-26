package com.orbstest

import com.orbstest.model._
import com.orbstest.view.{CliViewer, Viewer}

import scala.util.{Success, Try}

/**
  * Created by Michael on 12/25/2018.
  */
object Game extends App {
  def generateRandomBoard(size: Int) = {
    val numbers = scala.util.Random.shuffle(1 to size * size).toSeq

    var index = 0
    val matrix = Array.ofDim[Square](size, size)
    for {
      i <- 0 until size
      j <- 0 until size
    } yield {
      matrix(i)(j) = numbers(index) match {
        case num if num == size * size => EmptySquare
        case num => NumSquare(num)
      }
      index += 1
    }

    matrix
  }

  val boardSize = 4
  var turn = new Turn(generateRandomBoard(boardSize))

  val viewer: Viewer = new CliViewer

  while(!turn.isSolved) {
    println("Current state")
    viewer.show(turn.board)

    println("Please select a number on the board")
    val selectedNumTry = Try(scala.io.StdIn.readInt())
    println("Please select move direction (w - UP, s - DOWN, a - LEFT, d - RIGHT)")
    val selectedDirectionTry = Try(scala.io.StdIn.readChar())

    (selectedNumTry, selectedDirectionTry) match {
      case (Success(selectedNum), Success(selectedDirection)) =>
        turn.makeMove(selectedNum, selectedDirection) match {
          case Left(IllegalMove(msg)) => println(s"Illegal move: $msg !!!")
          case Right(newTurn)         => turn = newTurn
        }

      case _ => println("Bad input!!!")
    }


  }
  println("Congratulations you solved the puzzle!!!")
  viewer.show(turn.board)
}
