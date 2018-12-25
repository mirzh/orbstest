package com.orbstest.view

import com.orbstest.model.{EmptySquare, NumSquare, Square}

/**
  * Created by Michael on 12/25/2018.
  */
trait Viewer {
  def show(board: Array[Array[Square]])
}

class CliViewer extends Viewer {
  override def show(board: Array[Array[Square]]): Unit = {
    val boardRepresentation = board.map { row =>
      row.map { cell =>
        cell match {
          case NumSquare(num) if num < 10 => s"|  $num |"
          case NumSquare(num)             => s"| $num |"
          case EmptySquare                => s"|    |"
        }
      }.mkString
    }

    println(boardRepresentation.mkString("\n"))
  }
}