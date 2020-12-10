package com.c2v4.advent20

import arrow.syntax.function.memoize

fun jolt2(input: String) =
    input.split(splitRegex).map { it.toInt() }.sorted().let { arrangementsMemoized(it) }

val arrangementsMemoized =
    { list: List<Int> ->
      var arrangements: (Int) -> Long = { it.toLong() }
      arrangements =
          { currentItem: Int ->
                if (currentItem < 1) 1L
                else
                    setOf(1, 2, 3)
                        .map { list[currentItem] - it }
                        .filter { list.contains(it) || it == 0 }
                        .map {
                            arrangements(list.indexOf(it))
                        }
                        .sum()
              }.memoize()
      arrangements(list.size - 1)
    }


fun main(args: Array<String>) {
  println(jolt2("day10.txt".asResource()))
}
