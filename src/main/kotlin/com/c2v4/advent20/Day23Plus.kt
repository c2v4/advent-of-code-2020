package com.c2v4.advent20

import java.util.*

fun cups2(input: String, rounds: Int, numberOfCups: Int = 1_000_000) =
    input
        .toList()
        .map { it - '0' }
        .let { LinkedList(it.plus((it.size + 1)..numberOfCups)) }
        .let { list ->
          val nodes = list.map { Node(it) }
          nodes.zipWithNext().forEach { (first, second) -> first.nextBe(second) }
          nodes.first().previousBe(nodes.last())
          val map = TreeMap<Int, Node>()
          nodes.forEach { map.put(it.value, it) }
          nodes.first() to map
        }
        .let { (node, map) ->
          var currentNode = node
          repeat(rounds) {
            val currentCup = currentNode.value
            val pickedUp =
                listOf(
                    currentNode.next!!, currentNode.next!!.next!!, currentNode.next!!.next!!.next!!)
            currentNode.nextBe(currentNode.next?.next?.next?.next!!)
            val destination = getDestination(currentCup, pickedUp, map)
            val destinationNext = destination.next!!
            destination.nextBe(pickedUp.first())
            destinationNext.previousBe(pickedUp.last())
            currentNode = currentNode.next!!
          }
          map
        }
        .let { it[1]!!.next!!.value.toLong() * it[1]!!.next!!.next!!.value }

class Node(val value: Int) {
  var next: Node? = null
  var previous: Node? = null

  fun nextBe(value: Node) {
    next = value
    value.previous = this
  }

  fun previousBe(value: Node) {
    previous = value
    value.next = this
  }
}

private fun getDestination(currentCup: Int, pickedUp: List<Node>, acc: TreeMap<Int, Node>): Node {
  if (currentCup == 1)
      return getDestination(
          acc.keys.maxOrNull()?.plus(1) ?: throw IllegalStateException(), pickedUp, acc)
  if (pickedUp.any { it.value == currentCup - 1 })
      return getDestination(currentCup - 1, pickedUp, acc)
  return acc[currentCup - 1]!!
}

fun main(args: Array<String>) {
  println(cups2("789465123", 10_000_000))
}
