package com.c2v4.advent20

import com.google.common.graph.ValueGraph

fun haversacks2(input: String) = countNext(createBagGraph(input), "shiny gold") - 1

private fun <T> countNext(graph: ValueGraph<T, Int>, currentNode: T): Int {
  val successors = graph.successors(currentNode)
  if (successors.isEmpty()) return 1
  return 1 + successors.map { graph.edgeValue(currentNode, it).get() * (countNext(graph, it)) }.sum()
}

fun main(args: Array<String>) {
  println(haversacks2("day7.txt".asResource()))
}
