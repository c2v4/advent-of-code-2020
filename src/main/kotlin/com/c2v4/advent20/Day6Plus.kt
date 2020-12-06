package com.c2v4.advent20

import com.google.common.collect.Sets

fun customs2(input: String) =
    input
        .split(Regex("\\r?\\n\\r?\\n"))
        .map { s ->
          s.split(splitRegex).map { string -> string.toCharArray().toSet() }.reduceRight {
          set,
          acc ->
            Sets.intersection(set, acc)
          }.size
        }
        .sum()

fun main(args: Array<String>) {
  println(customs2("day6.txt".asResource()))
}
