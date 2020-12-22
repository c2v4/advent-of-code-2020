package com.c2v4.advent20

fun conway2(input: String, cycles: Int) =
    input
        .split(EOL)
        .mapIndexed { y, s ->
          s.mapIndexed { x, c -> if (c == '#') Point4(x, y, 0, 0) else null }.filterNotNull()
        }
        .flatten()
        .toSet()
        .let { points ->
          (0 until cycles)
              .fold(
                  points,
                  { acc, _ ->
                    getRange(acc) { it.x }
                        .flatMap { x ->
                          getRange(acc) { it.y }.flatMap { y ->
                            getRange(acc) { it.z }.flatMap { z ->
                              getRange(acc) { it.w }.map { w -> Point4(x, y, z, w) }
                            }
                          }
                        }
                        .filter { shouldBeActive(it, acc) }
                        .toSet()
                  })
              .count()
        }

private fun shouldBeActive(point: Point4, activePoints: Set<Point4>): Boolean =
    getNeighbours(point).filter { activePoints.contains(it) }.count() in
        if (activePoints.contains(point)) 2..3 else 3..3

private fun getRange(acc: Set<Point4>, getter: (Point4) -> Int) =
    (getter(acc.minBy { getter(it) }!!) - 1)..(getter(acc.maxBy { getter(it) }!!) + 1)

private val NEIGHBOUR_RANGE = -1..1

private fun getNeighbours(point: Point4, neighbourRange: IntRange = NEIGHBOUR_RANGE): Set<Point4> =
    neighbourRange
        .flatMap { x ->
          neighbourRange.flatMap { y ->
            neighbourRange.flatMap { z ->
              neighbourRange.map { w -> Point4(point.x + x, point.y + y, point.z + z, point.w + w) }
            }
          }
        }
        .minus(point)
        .toSet()

data class Point4(val x: Int, val y: Int, val z: Int, val w: Int)

fun main(args: Array<String>) {
  println(conway2("day17.txt".asResource(), 6))
}
