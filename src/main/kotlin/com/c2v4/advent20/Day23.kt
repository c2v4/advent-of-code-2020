package com.c2v4.advent20

fun cups(input: String, rounds: Int) =
    input
        .toList()
        .map { it - '0' }
        .let { list ->
          (0 until rounds).fold(list to 0) { (acc, i), _ ->
            val pointer = i modulo acc.size
            val currentCup = acc[pointer]
            val pickedUp = (acc + acc).subList(pointer + 1, pointer + 4)
            val intermediate = acc.minus(pickedUp)
            val destination =
                intermediate.indexOf(
                    intermediate.map { currentCup - it }.filter { it > 0 }.minOrNull()?.let {
                      currentCup - it
                    }
                        ?: intermediate.maxOrNull() ?: throw IllegalStateException("List is empty"))

            val newList =
                intermediate.subList(0, destination + 1) +
                    pickedUp +
                    intermediate.subList(destination + 1, intermediate.size)
            newList to (newList.indexOf(currentCup) + 1 modulo newList.size)
          }
        }
        .first
        .let {
            (it+it).joinToString("").substringAfter('1').substringBefore('1')
        }

fun main(args: Array<String>) {
  println(cups("789465123",100))
}
