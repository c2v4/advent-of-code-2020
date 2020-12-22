package com.c2v4.advent20

fun jolt(input: String) =
    input.split(EOL).map{it.toInt()}.sorted().fold((0 to 1) to 0)
    {(jumps,previous), i ->
        val jump = i-previous
        val newJumps = when (jump){
            1 -> jumps.first+1 to jumps.second
            3 -> jumps.first to jumps.second+1
            else -> jumps.first to jumps.second
        }
        newJumps to i
    }.let {
        it.first.first * it.first.second
    }

fun main(args: Array<String>) {
  println(jolt("day10.txt".asResource()))
}
