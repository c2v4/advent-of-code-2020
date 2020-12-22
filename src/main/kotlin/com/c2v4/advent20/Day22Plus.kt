package com.c2v4.advent20

fun combat2(input: String) =
    input
        .split(PARAGRAPH)
        .map { it.split(EOL).drop(1).map { it.toInt() } }
        .let { playGame(it[0], it[1]).first }
        .reversed()
        .foldIndexed(0L) { index, acc, i -> acc + ((index + 1) * i) }

private fun playGame(
    player1: List<Int>,
    player2: List<Int>,
    p1visited: Set<List<Int>> = emptySet(),
    p2visited: Set<List<Int>> = emptySet()
): Pair<List<Int>, Int> {
  if (player1.isEmpty()) return player2 to 2
  if (player2.isEmpty()) return player1 to 1
  if (p1visited.contains(player1) || p2visited.contains(player2)) return player1 to 1
  val (f1, f2) = player1.first() to player2.first()
  val (p1, p2) = player1.drop(1) to player2.drop(1)
  val winner = determineWinner(f1, f2, p1, p2)
  return if (winner == 1)
      playGame(
          p1.plusElement(f1).plusElement(f2),
          p2,
          p1visited.plusElement(player1),
          p2visited.plusElement(player2))
  else
      playGame(
          p1,
          p2.plusElement(f2).plusElement(f1),
          p1visited.plusElement(player1),
          p2visited.plusElement(player2))
}

fun determineWinner(f1: Int, f2: Int, p1: List<Int>, p2: List<Int>): Int {
  if (f1 > p1.size || f2 > p2.size) return if (f1 > f2) 1 else 2
  return playGame(p1.take(f1), p2.take(f2)).second
}

fun main(args: Array<String>) {
  println(combat2("day22.txt".asResource()))
}
