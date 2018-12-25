package com.orbstest.model

import org.scalatest.FlatSpec

/**
  * Created by Michael on 12/25/2018.
  */
class ModelTests extends FlatSpec {
  val gameBoard : Array[Array[Square]] = Array(
    Array(NumSquare(1), NumSquare(2)),
    Array(EmptySquare, NumSquare(3))
  )

  val turn = new Turn(gameBoard)

  "moving square 3 to the left" should "be valid" in {
    assert( turn.makeMove(3, 'a').isRight )
  }

  "moving square 3 to the left" should "win the game" in {
    assert( turn.makeMove(3, 'a').right.map(_.isSolved).right.getOrElse(false) )
  }

  "moving square 2 to the right" should "be illegal" in {
    assert( turn.makeMove(2, 'a').isLeft )
  }

  "moving square 2 to the left" should "be illegal" in {
    assert( turn.makeMove(2, 'd').isLeft )
  }

}
