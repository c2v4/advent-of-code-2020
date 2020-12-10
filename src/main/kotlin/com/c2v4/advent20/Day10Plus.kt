package com.c2v4.advent20

import arrow.syntax.function.memoize

fun jolt2(input: String) =
    input.split(splitRegex).map{it.toInt()}.sorted().let { arrangementsMemoized(it) }

val arrangementsMemoized = {list:List<Int> -> arrangements(list)}.memoize()

fun arrangements(input:List<Int>):Int{
    if(input.size<2) return 1
    val currentMax = input.max()!!
    return setOf(1,2,3)
        .map { currentMax - it }
        .filter { input.contains(it) }
        .map{ arrangements(input.subList(0,input.indexOf(it)+1))}
        .sum()
    }



fun main(args: Array<String>) {
  println(jolt2("day10.txt".asResource()))
}
