package com.c2v4.advent20

import com.google.common.collect.Sets
import java.lang.IllegalStateException

fun allergen2(input: String) =
    input
        .split(EOL)
        .map {
          val split = it.split(" (contains ")
          split[0].split(" ") to split[1].dropLast(1).split(", ")
        }
        .let { list ->
          val allergenMap =
              list.flatMap { pair -> pair.second.map { it to pair.first.toSet() } }.fold(
                      mutableMapOf<String, Set<String>>()) { acc, pair ->
                acc.merge(pair.first, pair.second) { a, b -> Sets.intersection(a, b) }
                acc
              }
            reduceMap(allergenMap).entries.sortedBy { it.key }.map { it.value }.joinToString(",")
        }

private fun reduceMap(map: Map<String, Set<String>>): Map<String, String> {
  if (map.size == 1) return mapOf(map.keys.first() to map.values.first().first())
  val (key, value) = map.entries.find { it.value.size == 1 }
      ?: throw IllegalStateException("Cannot find single value set")
  return mapOf(key to value.first()) +
      reduceMap(map.minus(key).mapValues { entry -> entry.value.minus(value.first()) })
}

fun main(args: Array<String>) {
  println(allergen2("day21.txt".asResource()))
}
