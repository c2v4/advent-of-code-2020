package com.c2v4.advent20

fun reportRepair2(input: String): Int =
    input.split(EOL).map { it.toInt() }.sorted().let {
      var i = 0
      var j = 1
      var k = it.size - 1
      while (i < it.size / 2) {
        while (j < k) {
          val value = it[i] + it[j] + it[k]
          if (value == 2020) {
            return it[i] * it[k] * it[j]
          }
          if (value > 2020) {
            k--
          } else {
            j++
          }
        }
        i++
          j=i+1
          k=it.size-1
      }
      return -1
    }

fun main(args: Array<String>) {
  println(reportRepair2("day1.txt".asResource()))
}
