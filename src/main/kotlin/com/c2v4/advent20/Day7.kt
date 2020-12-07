package com.c2v4.advent20

import com.google.common.graph.ValueGraph
import com.google.common.graph.ValueGraphBuilder

fun haversacks(input: String) = countNext(createBagGraph(input), "shiny gold").size

fun createBagGraph(input: String): ValueGraph<String, Int> =
    input
        .split(splitRegex)
        .map { s ->
          val split = s.split(Regex(" bags? contain "))
          split[0] to split[1].split(", ")
        }
        .fold(ValueGraphBuilder.directed().build<String, Int>()) { acc, pair ->
          if (pair.second.size == 1 && pair.second[0] == "no other bags.") {
            acc.addNode(pair.first)
          } else {
            pair.second.forEach {
              val value = it.substringBefore(' ').toInt()
              val node = it.substringAfter(' ').substringBefore(" bag")
              acc.putEdgeValue(pair.first, node, value)
            }
          }
          acc
        }

private fun <T, U> countNext(graph: ValueGraph<T, U>, currentNode: T): Set<T> {
  val predecessors = graph.predecessors(currentNode)
  if (predecessors.isEmpty()) return emptySet()
  return predecessors.toSet() + predecessors.flatMap { countNext(graph, it) }
}

fun main(args: Array<String>) {
  println(haversacks("day7.txt".asResource()))
}
