package com.c2v4.advent20

fun shuttle(input: String) =
    input.split(EOL).let {
      it.first().toInt() to it[1].split(",").filter { id -> id != "x" }.map { id -> id.toInt() }
    }.let {
        (timestamp,ids) ->
        ids.map{it to it-(timestamp % it)}.minBy { it.second }?.let { (id,time)-> id*time }
    }

fun main(args: Array<String>) {
  println(shuttle("day13.txt".asResource()))
}
