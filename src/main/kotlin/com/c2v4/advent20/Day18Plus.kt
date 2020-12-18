package com.c2v4.advent20

import java.lang.IllegalStateException

fun operation2(input: String): Long {
  val whitespaceRegex = Regex("\\s")
  return input
      .split(splitRegex)
      .map { line -> calculate(line.split(whitespaceRegex)).first().toLong() }
      .sum()
}

private val ADDITION = { i: Long, j: Long -> i + j }

private val MULTIPLICATION = { i: Long, j: Long -> i * j }

private val NO_OP = { i: Long, j: Long -> throw IllegalStateException() }

private val OPERATOR_DICTIONARY = mapOf("+" to ADDITION, "*" to MULTIPLICATION)

private fun calculate(tokens: List<String>): List<String> {
  if (tokens.size == 1) return tokens
  val indexOfFirstParenthesis = tokens.indexOfFirst { token -> token.startsWith('(') }
  if (indexOfFirstParenthesis != -1) {
    val indexOfClosing = findClosingParenthesisIndex(tokens,indexOfFirstParenthesis)
    val innerTokens = tokens.subList(indexOfFirstParenthesis, indexOfClosing + 1).toMutableList()
    // get rid of parenthesis
    innerTokens[0] = innerTokens.first().drop(1)
    innerTokens[innerTokens.size - 1] = innerTokens.last().dropLast(1)
    return calculate(
        tokens.subList(0, indexOfFirstParenthesis) +
            calculate(innerTokens) +
            tokens.subList(indexOfClosing + 1, tokens.size))
  }
  val indexOfPlus = tokens.indexOf("+")
  if (indexOfPlus != -1) {
    return calculate(
        tokens.subList(0, indexOfPlus - 1) +
            (tokens[indexOfPlus - 1].toLong() + tokens[indexOfPlus + 1].toLong()).toString() +
            tokens.subList(indexOfPlus + 2, tokens.size))
  }
  return listOf(tokens.minus(setOf("*")).map { it.toLong() }.fold(1L, MULTIPLICATION).toString())
}

fun main(args: Array<String>) {
  println(operation2("day18.txt".asResource()))
}
