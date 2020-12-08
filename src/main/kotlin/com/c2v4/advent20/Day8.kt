package com.c2v4.advent20

fun handheld(input: String) =
    input
        .split(splitRegex)
        .map {
          val split = it.split(" ")
          split[0] to split[1]
        }
        .run { calculate(State(this)) { state -> state.visited.contains(state.pointer) } }
        .acc

fun calculate(state: State, endCondition: (State) -> Boolean): State {
  if (endCondition(state)) return state
  val (instruction, parameter) = state.program[state.pointer]

  instructions[instruction]?.let { it(state, parameter) } ?: throw IllegalStateException()
  return calculate(state, endCondition)
}

data class State(
    val program: List<Pair<String, String>>,
    var pointer: Int = 0,
    var acc: Int = 0,
    val visited: MutableSet<Int> = mutableSetOf())

val instructions =
    mapOf<String, (State, String) -> State>(
        "nop" to
            { state, _ ->
              state.apply {
                visited.add(pointer)
                pointer++
              }
            },
        "jmp" to
            { state, s ->
              state.apply {
                visited.add(pointer)
                pointer += s.toInt()
              }
            },
        "acc" to
            { state, s ->
              state.apply {
                visited.add(pointer)
                acc += s.toInt()
                pointer++
              }
            })

fun main(args: Array<String>) {
  println(handheld("day8.txt".asResource()))
}
