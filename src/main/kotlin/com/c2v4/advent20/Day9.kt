package com.c2v4.advent20

fun encoding(input: String, preambleSize: Int = 25) =
    input.split(EOL).map { it.toLong() }.let { list ->
        list.windowed(preambleSize + 1, 1).find {
            val last = it.last()
            val preamble = it.take(preambleSize)
            preamble.none { number -> preamble.contains( last - number) }
        }?.last()
    }

fun main(args: Array<String>) {
  println(encoding("day9.txt".asResource()))
}
