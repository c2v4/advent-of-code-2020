package com.c2v4.advent20

fun conway(input: String, cycles: Int) =
    input
        .split(EOL)
        .mapIndexed { y, s ->
          s.mapIndexed { x, c -> if (c == '#') Point3(x, y, 0) else null }.filterNotNull()
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
                            getRange(acc) { it.z }.map { z -> Point3(x, y, z) }
                          }
                        }
                        .filter { shouldBeActive(it, acc) }
                        .toSet()
                  })
              .count()
        }

private fun shouldBeActive(point3: Point3, activePoints: Set<Point3>): Boolean =
    getNeighbours(point3).filter { activePoints.contains(it) }.count() in
        if (activePoints.contains(point3)) 2..3 else 3..3

private fun getRange(acc: Set<Point3>, getter: (Point3) -> Int) =
    (getter(acc.minBy { getter(it) }!!) - 1)..(getter(acc.maxBy { getter(it) }!!) + 1)

private val NEIGHBOUR_RANGE = -1..1

private fun getNeighbours(point: Point3, neighbourRange: IntRange = NEIGHBOUR_RANGE): Set<Point3> =
    neighbourRange
        .flatMap { x ->
          neighbourRange.flatMap { y ->
            neighbourRange.map { z -> Point3(point.x + x, point.y + y, point.z + z) }
          }
        }
        .minus(point)
        .toSet()

data class Point3(val x: Int, val y: Int, val z: Int)

fun main(args: Array<String>) {
  println(conway("day17.txt".asResource(), 6))
}
