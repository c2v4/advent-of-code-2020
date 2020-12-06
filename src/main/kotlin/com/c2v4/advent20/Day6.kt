package com.c2v4.advent20

fun customs(input: String) =
    input.split(Regex("\\r?\\n\\r?\\n"))
        .map { s -> s.split(splitRegex).flatMap { s -> s.toCharArray().toSet() }.toSet().size }
        .sum()

fun main(args: Array<String>) {
  println(customs("day6.txt".asResource()))
}
