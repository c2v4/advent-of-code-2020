package com.c2v4.advent20

fun lobby(input: String) =
    getTiles(input).size

fun getTiles(input: String) = input.split(EOL).map { toPoint2D(it) }.fold(emptySet<Point2>()) { acc, point ->
    if (acc.contains(point)) acc - point else acc + point
}


fun toPoint2D(input: String): Point2 {
    if(input.isEmpty()) return Point2(0,0)
    return when(input.first()){
        'w' -> Point2(-1,0) + toPoint2D(input.drop(1))
        'e' -> Point2(1,0) + toPoint2D(input.drop(1))
        's' -> (if(input[1] == 'e') Point2(1,-1) else Point2(0,-1) )+ toPoint2D(input.drop(2))
        'n' -> (if(input[1] == 'e') Point2(0,1) else Point2(-1,1) )+ toPoint2D(input.drop(2))
        else -> throw IllegalArgumentException()
    }
}

fun main(args: Array<String>) {
  println(lobby("day24.txt".asResource()))
}
