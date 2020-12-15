package com.c2v4.advent20

fun recitation(input: String, till:Int = 2020) = input.split(",").map { it.toInt() }.let{ startingList ->
  val initialMap = startingList.mapIndexed { index, i -> i to (-1 to index) }.toMap().toMutableMap()
  generateSequence((initialMap.size to startingList.last()) to initialMap) { (currentState,store) ->
    val (index,last) = currentState
    var (indexOfPreviousLast,indexOfLast) = store.getOrDefault(last, -1 to -1)
    val differenceFromLast = if(indexOfPreviousLast==-1) 0  else indexOfLast-indexOfPreviousLast
    indexOfLast = if(indexOfPreviousLast==-1) store[0]!!.second else store[differenceFromLast]?.second?:-1
    store[differenceFromLast] = indexOfLast to index
    (index+1 to differenceFromLast) to store
  }.first { (state,_) ->
    val (index,last) = state
    index == till
  }.first.second

}

fun main(args: Array<String>) {
  println(recitation("2,0,6,12,1,3"))
}
