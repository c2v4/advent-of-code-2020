package com.c2v4.advent20

import java.lang.IllegalArgumentException

val binaryConverter =
    { c: Char ->
      when (c) {
        'R', 'B' -> '1'
        'F', 'L' -> '0'
        else -> throw IllegalArgumentException()
      }
    }

fun binary(input: String) =
    toSequenceOfSeats(input)
        .max()

fun toSequenceOfSeats(input: String) = input
    .split(splitRegex)
    .asSequence()
    .map { toBinary(it) }

private fun toBinary(it: String) =
    it.map(binaryConverter).joinToString("") { c -> c.toString() }.toInt(2)

fun main(args: Array<String>) {
  println(binary("day5.txt".asResource()))
}
