package com.c2v4.advent20

import java.lang.IllegalArgumentException
import kotlin.math.abs

fun rain2(input: String) =
    input
        .split(EOL)
        .fold((0 to 0) to (10 to 1)) { (ship, waypoint), s ->
          val key = s.first()
          val value = s.drop(1).toInt()
          when (key) {
            in Direction.ABBREVIATION.keys ->
                ship to Direction.ABBREVIATION[key]!!.modify(waypoint, value)
            'F' -> ship + waypoint * value to waypoint
            'L', 'R' -> ship to waypoint.rotate(value / 90 * if (key == 'L') 1 else -1)
            else -> throw IllegalArgumentException()
          }
        }
        .first
        .let { (x, y) -> abs(x) + abs(y) }

private fun Pair<Int, Int>.rotate(times: Int): Pair<Int, Int> {
  val (sn, cs) = ROTATION_VALUES[times modulo ROTATION_VALUES.size]
  return this.first * cs - this.second * sn to this.first * sn + this.second * cs
}

private val ROTATION_VALUES = arrayOf(0 to 1,1 to 0, 0 to -1, -1 to 0)

private operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> =
    this.first + other.first to this.second + other.second

private operator fun Pair<Int, Int>.times(value: Int): Pair<Int, Int> =
    this.first * value to this.second * value

fun main(args: Array<String>) {
  println(rain2("day12.txt".asResource()))
}
