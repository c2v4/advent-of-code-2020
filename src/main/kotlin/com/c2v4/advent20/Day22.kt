package com.c2v4.advent20

fun combat(input: String) =
    input
        .split(PARAGRAPH)
        .map { it.split(EOL).drop(1).map { it.toInt() } }
        .let { playGame(it[0], it[1]) }
        .reversed()
        .foldIndexed(0L) { index, acc, i ->  acc + ((index + 1) * i) }

private fun playGame(player1: List<Int>, player2: List<Int>): List<Int> {
  if (player1.isEmpty()) return player2
  if (player2.isEmpty()) return player1
  val (f1, f2) = player1.first() to player2.first()
  val (p1, p2) = player1.drop(1) to player2.drop(1)
  return if (f1 > f2) playGame(p1.plusElement(f1).plusElement(f2), p2)
  else playGame(p1, p2.plusElement(f2).plusElement(f1))
}

fun main(args: Array<String>) {
  println(combat("day22.txt".asResource()))
}
