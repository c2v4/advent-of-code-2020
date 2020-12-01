package com.c2v4.advent20

fun reportRepair(input: String) = input.split(splitRegex)
        .map { it.toInt() }
        .fold(mutableSetOf<Int>(), { acc, i ->  if(acc.contains(i)){
          return i*(2020-i)
        }
          acc.add(2020-i)
          acc
        })
        .let { -1 }

fun main(args: Array<String>) {
  println(reportRepair("day1.txt".asResource()))
}
