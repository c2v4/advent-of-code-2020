package com.c2v4.advent20

fun encoding2(input: String, preambleSize: Int = 25) =
    input.split(EOL).map { it.toLong() }.let { list ->
      val missing =
          list.windowed(preambleSize + 1, 1)
              .find {
                val last = it.last()
                val preamble = it.take(preambleSize)
                preamble.none { number -> preamble.contains(last - number) }
              }
              ?.last()
        missing?.let { findSummingSequence(list, it) }?.let { pointers ->
            val subList = list.subList(pointers.first, pointers.second)
            subList.max()?.plus(subList.min()!!)
        }
    }

private fun findSummingSequence(
    wholeSequence: List<Long>,
    sum: Long,
    currentSum: Long = wholeSequence[0],
    pointers: Pair<Int,Int> = 0 to 0
): Pair<Int,Int> {
    if (pointers.first > pointers.second) return -1 to -1
    if(currentSum==sum) return pointers
    if(currentSum>sum) return findSummingSequence(wholeSequence, sum, currentSum - wholeSequence[pointers.first], pointers.first+1 to pointers.second)
    return findSummingSequence(wholeSequence, sum, currentSum + wholeSequence[pointers.second+1], pointers.first to pointers.second+1)
}

fun main(args: Array<String>) {
  println(encoding2("day9.txt".asResource()))
}
