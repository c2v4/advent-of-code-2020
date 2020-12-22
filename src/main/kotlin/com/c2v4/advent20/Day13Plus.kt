package com.c2v4.advent20

fun shuttle2(input: String) =
    input
        .split(EOL)
        .let { list ->
          list[1]
              .split(",")
              .asSequence()
              .withIndex()
              .filter { id -> id.value != "x" }
              .map { it.value.toLong() - it.index to it.value.toLong() }
              .toList()
        } //chinese remainder theorem
        .let { list ->
          val commonModulo = list.map { it.second }.fold(1L, { acc, i -> acc * i })
          list.map { (a, m) -> val b = commonModulo / m
              a * b * modInv(b,m)!!
          }.sum() % commonModulo
        }

fun modInv(a: Long, c: Long): Long? =
  (1 until c).find { (a * it) % c == 1L }


fun main(args: Array<String>) {
  println(shuttle2("day13.txt".asResource()))
}
