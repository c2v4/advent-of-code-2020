package com.c2v4.advent20

fun binary2(input: String) =
    toSequenceOfSeats(input).toSet().sorted().let {
      it.windowed(2).find { list -> list[0] == list[1] - 2 }?.let { list -> list[0] + 1 }
    }

fun main(args: Array<String>) {
  println(binary2("day5.txt".asResource()))
}
