package com.c2v4.advent20

import java.lang.IllegalStateException

fun ticket2(input: String, allFieldsStartingWith: String = "") =
    createTicketUniverse(input.split(Regex("\\r?\\n\\r?\\n")).map { it.split(EOL) })
        .let { universe ->
          universe.copy(
              nearbyTickets =
                  universe.nearbyTickets.filter { list ->
                    list.all { value -> universe.rules.values.flatten().any { value in it } }
                  })
        }
        .let { universe ->
          val fieldMap = reduceToSinglePossibility(identifyFields(universe))
          fieldMap
              .filter { it.key.startsWith(allFieldsStartingWith) }
              .map { universe.myTicket[it.value] }
              .fold(1L) { acc, i -> acc * i }
        }

private fun identifyFields(universe: TicketUniverse) =
    universe
        .rules
        .map { rule ->
          val indices = universe.myTicket.indices
          rule.key to
              indices.filter { index ->
                universe.nearbyTickets.map { it[index] }.all { value ->
                  rule.value.any { value in it }
                }
              }
        }
        .toMap()

private fun reduceToSinglePossibility(input: Map<String, List<Int>>): Map<String, Int> {
  if (input.isEmpty()) return emptyMap()
  val singlePossibility =
      input.entries.find { it.value.size == 1 }?.let { it.key to it.value.first() }
          ?: throw IllegalStateException("No single possibility in map:$input")
  return mapOf(singlePossibility)
      .plus(
          reduceToSinglePossibility(
              input.minus(singlePossibility.first).mapValues {
                it.value.minusElement(singlePossibility.second)
              }))
}

fun main(args: Array<String>) {
  println(ticket2("day16.txt".asResource(),"departure"))
}
