package com.orbstest.model


case class IllegalMove(msg: String)

trait Square
case class NumSquare(num: Int) extends Square
case object EmptySquare extends Square

/**
  * Created by Michael on 12/25/2018.
  */
class Turn(val board: Array[Array[Square]]) {
  private def getSquareByIndices(i: Int, j: Int): Option[Square] = board.lift(i).map(_.lift(j)).flatten

  private def getPositionOf(num: Int): Option[(Int, Int)] = {
    (for {
      i <- 0 until board.size
      j <- 0 until board.size
    } yield {
      board(i)(j) match {
        case NumSquare(n) if n == num => Some((i, j))
        case _ => None
      }
    }).collect{
      case Some(tpl) => tpl
    }.headOption
  }

  private def getNeighbor(from: (Int, Int), direction: Char): Option[(Int, Int)] = {
    direction match {
      case 'w' => Some((from._1 - 1, from._2))
      case 's' => Some((from._1 + 1, from._2))
      case 'a' => Some((from._1, from._2 - 1))
      case 'd' => Some((from._1, from._2 + 1))
      case _   => None
    }

  }


  def makeMove(selected: Int, direction: Char): Either[IllegalMove , Turn] = {
    val from = getPositionOf(selected).getOrElse((-1,-1))
    val to   = getNeighbor(from, direction).getOrElse(from)

    val fromSquare = getSquareByIndices(from._1, from._2)
    val toSquare = getSquareByIndices(to._1, to._2)

    (fromSquare, toSquare) match {
      case (None, _)                                      => Left(IllegalMove("The source square is not on the board"))
      case (_, None)                                      => Left(IllegalMove("The target square is not on the board"))
      case (Some(EmptySquare), _)                         => Left(IllegalMove("You selected an empty square"))
      case (_, Some(NumSquare(_)))                        => Left(IllegalMove("You are attempting to move into not empty square"))
      case (Some(ns@NumSquare(_)), Some(es@EmptySquare))  =>
        val boardSwap1 = board.updated(from._1, board(from._1).updated(from._2, es))
        val boardSwap2 = boardSwap1.updated(to._1, boardSwap1(to._1).updated(to._2, ns))

        Right(new Turn(boardSwap2))
      case _                                              => Left(IllegalMove("This should not happened :("))
    }
  }

  private def generateSolvedBoard(size: Int) = {
    var index = 1
    val matrix = Array.ofDim[Square](size, size)
    for {
      i <- 0 until size
      j <- 0 until size
    } yield {
      matrix(i)(j) = NumSquare(index)
      index += 1
    }
    matrix(size - 1)(size - 1) = EmptySquare
    matrix
  }

  def isSolved: Boolean = {
    generateSolvedBoard(board.size).deep == board.deep
  }
}
