package com.c2v4.advent20


fun password(input: String) = input.split(splitRegex)
    .map { it.split(" ") }
    .count { val split = it[0].split("-")
        val intRange = split[0].toInt()..split[1].toInt()
        intRange.contains(it[2].count { char -> char == it[1].first() })
    }

fun main(args: Array<String>) {
    println(password("day2.txt".asResource()))
}
