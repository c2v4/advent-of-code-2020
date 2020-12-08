package com.c2v4.advent20

fun handheld2(input: String) =
    input
        .split(splitRegex)
        .map {
          val split = it.split(" ")
          split[0] to split[1]
        }
        .let {
          it
              .mapIndexed { index, (instruction, param) ->
                val newList = it.toMutableList()
                newList[index] = (instructionReplaces[instruction] ?: instruction) to param
                newList
              }
              .toSet()
        }
        .map {
          calculate(State(it)) { state ->
            state.visited.contains(state.pointer) || state.pointer >= state.program.size
          }
        }
        .find { state -> state.pointer >= state.program.size }?.acc

val instructionReplaces = mapOf("nop" to "jmp", "jmp" to "nop")

fun main(args: Array<String>) {
  println(handheld2("day8.txt".asResource()))
}
