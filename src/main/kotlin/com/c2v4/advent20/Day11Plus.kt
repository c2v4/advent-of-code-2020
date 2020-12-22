package com.c2v4.advent20

import java.lang.IllegalArgumentException

fun seating2(input: String) =
    input.split(EOL).let { list ->
      val grid = extractInitialGrid(list)
      generateSequence(Array(0) { Array(0) { SeatState.FLOOR } } to grid) { grids ->
        grids.second to stepGrid(grids.second)
      }
          .onEach {
            println()
            println(represent(it.second))
          }
          .takeWhile { grids -> !grids.first.contentDeepEquals(grids.second) }
          .last()
          .second
          .sumBy { row -> row.count { it == SeatState.OCCUPIED } }
    }

private fun extractInitialGrid(list: List<String>): Array<Array<SeatState>> =
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

private fun stepGrid(grid: Array<Array<SeatState>>): Array<Array<SeatState>> =
    Array(grid.size) { y ->
      Array(grid[y].size) { x ->
        val currentSeatState = grid[y][x]
        currentSeatState.transform(neighboursOccupied(grid, x, y), 5)
      }
    }

private fun neighboursOccupied(grid: Array<Array<SeatState>>, x: Int, y: Int): Int =
    NEIGHBOUR_OFFSETS
        .asSequence()
        .map { offset ->
          val newPair = x + offset.first to y + offset.second
          generateSequence(newPair) { pair ->
            pair.first + offset.first to pair.second + offset.second
          }
              .map {
                grid.getOrElse(it.second) { Array(0) { SeatState.EMPTY } }.getOrElse(it.first) {
                  SeatState.EMPTY
                }
              }
              .first { it != SeatState.FLOOR }
        }
        .count { it == SeatState.OCCUPIED }

fun main(args: Array<String>) {
  println(seating2("day11.txt".asResource()))
}
