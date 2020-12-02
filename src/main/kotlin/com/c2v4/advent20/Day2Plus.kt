package com.c2v4.advent20


fun password2(input: String) = input.split(splitRegex)
    .map { it.split(" ") }
    .count { val split = it[0].split("-").map { s -> s.toInt()-1 }
        val first = it[1].first()
        (it[2][split[0]] == first) xor (it[2][split[1]] == first)
    }

fun main(args: Array<String>) {
    println(password2("day2.txt".asResource()))
}
