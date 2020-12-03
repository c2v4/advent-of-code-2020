package com.c2v4.advent20


fun trajectory(input: String) = input.split(splitRegex)
    .foldIndexed(0){index, acc, s ->
        val i = (index*3)%s.length
        if(s[i]=='#') acc+1 else acc
    }

fun main(args: Array<String>) {
    println(trajectory("day3.txt".asResource()))
}
