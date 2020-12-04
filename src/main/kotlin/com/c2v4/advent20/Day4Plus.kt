package com.c2v4.advent20

import com.google.common.collect.Sets

val validations =
    mapOf<String, (String) -> Boolean>(
        "byr" to { s -> s.toInt() in 1920..2002 },
        "iyr" to { s -> s.toInt() in 2010..2020 },
        "eyr" to { s -> s.toInt() in 2020..2030 },
        "hgt" to
            { s ->
              val unit = s.takeLast(2)
              val value = s.dropLast(2).toInt()
              if (unit == "cm") value in 150..193 else value in 59..76
            },
        "hcl" to { s -> s.first() == '#' && s.drop(1).matches(Regex("[0-9a-fA-F]{6}")) },
        "ecl" to { s -> s in setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") },
        "pid" to { s -> s.matches(Regex("\\d{9}")) })

fun passport2(input: String) =
    input
        .split(Regex("\\r?\\n\\r?\\n"))
        .map { it.split(Regex("\\s")) }
        .filter {
          Sets.difference(validations.keys, it.map { s -> s.split(":")[0] }.toSet()).isEmpty()
        }
        .count {
          it.all {
            val key = it.split(":")[0]
            val value = it.split(":")[1]
            try {
              validations.getOrDefault(key) { true }(value)
            } catch (e: Exception) {
              return@all false
            }
          }
        }

fun main(args: Array<String>) {
  println(passport2("day4.txt".asResource()))
}
