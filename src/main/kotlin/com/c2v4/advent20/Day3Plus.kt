package com.c2v4.advent20

fun trajectory2(input: String) =
    setOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2)
        .map { (right, down) ->
          input.split(EOL).foldIndexed(0) { index, acc, s ->
            val i = (index * right / down) % s.length
            if (index % down == 0 && s[i] == '#') acc + 1 else acc
          }
        }
        .product()

fun main(args: Array<String>) {
  println(trajectory2("day3.txt".asResource()))
}

fun Iterable<Int>.product() = fold(1) { acc, i -> acc * i }
