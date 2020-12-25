package com.c2v4.advent20

fun lobby2(input: String, days: Int = 100) =
    getTiles(input).let { startTiles ->
        (1..days).fold(startTiles) { acc, _ ->
            acc.flatMap { getNeighbours(it) + it }.filter { shouldBeBlack(it, acc) }.toSet()
        }
    }.size


fun shouldBeBlack(point: Point2, blackPoints: Set<Point2>): Boolean {
    val activeNeighbours = getNeighbours(point).count { blackPoints.contains(it) }
    return if (blackPoints.contains(point)) activeNeighbours in 1..2 else activeNeighbours == 2
}

private val NEIGHBOUR_POSITIONS = setOf(
    Point2(-1, 0), Point2(-1, 1), Point2(0, 1), Point2(1, 0),
    Point2(1, -1), Point2(0, -1)
)

private fun getNeighbours(point: Point2) = NEIGHBOUR_POSITIONS.map { it + point }

fun main(args: Array<String>) {
    println(lobby2("day24.txt".asResource()))
}
