package com.c2v4.advent20

fun ticket(input: String) =
    createTicketUniverse(input.split(Regex("\\r?\\n\\r?\\n")).map { it.split(splitRegex) }).let {
    universe ->
      universe
          .nearbyTickets
          .flatten()
          .filter { value -> universe.rules.values.flatten().none { value in it } }
          .sum()
    }

fun createTicketUniverse(ticketInput: List<List<String>>) =
    TicketUniverse(
        extractRules(ticketInput[0]),
        extractTickets(ticketInput[1].drop(1))[0],
        extractTickets(ticketInput[2].drop(1)))

private fun extractTickets(lines: List<String>): List<List<Int>> =
    lines.map { line -> line.split(",").map { it.toInt() } }

private fun extractRules(list: List<String>): Map<String, List<IntRange>> =
    list.asSequence()
        .map { it.split(": ") }
        .map { line ->
          line[0] to
              line[1].split(" or ").map { it.split("-") }.map {
                IntRange(it[0].toInt(), it[1].toInt())
              }
        }
        .toMap()

data class TicketUniverse(
    val rules: Map<String, List<IntRange>>,
    val myTicket: List<Int>,
    val nearbyTickets: List<List<Int>>)

fun main(args: Array<String>) {
  println(ticket("day16.txt".asResource()))
}
