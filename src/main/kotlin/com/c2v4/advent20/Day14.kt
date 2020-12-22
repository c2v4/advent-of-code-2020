package com.c2v4.advent20

import java.lang.IllegalArgumentException

fun docking(input: String) =
    input.split(EOL).fold((0L to 0L) to mutableMapOf<Int, Long>()) { acc, s ->
      when {
        s.startsWith("mask") ->
            (s.substringAfter("= ").replace("X", "0").toLong(2) to
                s.substringAfter("= ").replace("X", "1").toLong(2)) to acc.second
        s.startsWith("mem") -> {
            val (orMask,andMask) = acc.first
            acc.second[s.substringBefore("]").drop(4).toInt()] =
                s.substringAfter("= ").toLong() or orMask and andMask
          acc
        }
        else -> throw IllegalArgumentException()
      }
    }.second.values.sum()

fun main(args: Array<String>) {
  println(docking("day14.txt".asResource()))
}
