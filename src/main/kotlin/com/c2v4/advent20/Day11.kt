package com.c2v4.advent20

import java.lang.IllegalArgumentException

fun seating(input: String) =
    input.split(EOL).let { list ->
      val grid =
          Array(list.size) { y ->
            Array(list[y].length) { x ->
              when (list[y][x]) {
                '#' -> SeatState.OCCUPIED
                'L' -> SeatState.EMPTY
                '.' -> SeatState.FLOOR
                else -> throw IllegalArgumentException()
              }
            }
          }
      generateSequence(Array(0) { Array(0) { SeatState.FLOOR } } to grid) { grids ->
        grids.second to stepGrid(grids.second)
      }
          .onEach { println()
              println(represent(it.second)) }
          .takeWhile { grids -> !grids.first.contentDeepEquals(grids.second) }
          .last()
          .second
          .sumBy {
                  row -> row.count { it == SeatState.OCCUPIED }
          }
    }

fun represent(grid: Array<Array<SeatState>>): String =
    grid.joinToString("\n") { it.joinToString("") {seatState -> seatState.toString() } }

private fun stepGrid(grid: Array<Array<SeatState>>): Array<Array<SeatState>> =
    Array(grid.size) { y ->
      Array(grid[y].size) { x ->
        val currentSeatState = grid[y][x]
        currentSeatState.transform(neighboursOccupied(grid, x, y),4)
      }
    }

val NEIGHBOUR_OFFSETS =
    setOf(-1, 0, 1).let {
      it.flatMap { y -> it.map { x -> x to y } }.filter { offset -> offset != 0 to 0 }
    }

private fun neighboursOccupied(grid: Array<Array<SeatState>>, x: Int, y: Int): Int =
    NEIGHBOUR_OFFSETS
        .asSequence()
        .map { x + it.first to y + it.second }
        .map {
          grid.getOrElse(it.second) { Array(0) { SeatState.EMPTY } }.getOrElse(it.first) {
            SeatState.EMPTY
          }
        }
        .count { it == SeatState.OCCUPIED }

enum class SeatState(val transformFunction: (Int,Int) -> SeatState, private val character: Char) {
  EMPTY({ neighbours, _ -> if (neighbours == 0) OCCUPIED else EMPTY }, 'L'),
  OCCUPIED({ neighbours,tolerance -> if (neighbours > tolerance-1) EMPTY else OCCUPIED }, '#'),
  FLOOR({_,_ -> FLOOR }, '.');

  fun transform(neighboursOccupied: Int,tolerance:Int) = transformFunction(neighboursOccupied,tolerance)
  override fun toString() = character.toString()
}

fun main(args: Array<String>) {
  println(seating("day11.txt".asResource()))
}
