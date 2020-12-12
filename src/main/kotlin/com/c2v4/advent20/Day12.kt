package com.c2v4.advent20

import java.lang.IllegalArgumentException
import kotlin.math.abs

fun rain(input: String) =
    input
        .split(splitRegex)
        .foldRight((0 to 0) to Direction.EAST) { s, (position, direction) ->
          val key = s.first()
          val value = s.drop(1).toInt()
          when (key) {
            in Direction.ABBREVIATION.keys ->
                Direction.ABBREVIATION[key]!!.modify(position, value) to direction
            'F' -> direction.modify(position, value) to direction
            'L', 'R' -> position to direction.rotate(value / 90 * if (key == 'R') 1 else -1)
            else -> throw IllegalArgumentException()
          }
        }
        .first
        .let { (x, y) -> abs(x) + abs(y) }

enum class Direction(private val xModifier: Int, private val yModifier: Int) {
  NORTH(0, 1),
  EAST(1, 0),
  SOUTH(0, -1),
  WEST(-1, 0);

  companion object {
    val ORDER = arrayOf(NORTH, EAST, SOUTH, WEST)
    val ABBREVIATION = mapOf('N' to NORTH, 'S' to SOUTH, 'E' to EAST, 'W' to WEST)
  }

  fun modify(position: Pair<Int, Int>, times: Int) =
      position.first + xModifier * times to position.second + yModifier * times

  fun rotate(times: Int) = ORDER[(ORDER.indexOf(this) + times) modulo ORDER.size]
}

infix fun Int.modulo(other: Int) = ((this % other) + other) % other

fun main(args: Array<String>) {
  println(rain("day12.txt".asResource()))
}
