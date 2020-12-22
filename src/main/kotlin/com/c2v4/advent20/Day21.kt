package com.c2v4.advent20

import com.google.common.collect.Sets

fun allergen(input: String) =
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
          val productsWithAllergens = allergenMap.values.flatten().toSet()
          val frequencyMap = list.flatMap { it.first }.groupingBy { it }.eachCount()
          frequencyMap.filter { !productsWithAllergens.contains(it.key) }.map { it.value }.sum()
        }

fun main(args: Array<String>) {
  println(allergen("day21.txt".asResource()))
}
