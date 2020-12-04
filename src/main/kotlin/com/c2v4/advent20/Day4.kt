package com.c2v4.advent20

import com.google.common.collect.Sets

private val requiredFields = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

fun passport(input: String) =
    input.split(Regex("\\r?\\n\\r?\\n")).map { it.split(Regex("\\s")) }.count {
      Sets.difference(requiredFields, it.map { s -> s.split(":")[0] }.toSet()).isEmpty()
    }

fun main(args: Array<String>) {
  println(passport("day4.txt".asResource()))
}
