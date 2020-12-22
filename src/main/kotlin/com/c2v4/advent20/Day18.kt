package com.c2v4.advent20

import java.lang.IllegalStateException

fun operation(input: String): Long {
  val whitespaceRegex = Regex("\\s")
  return input.split(EOL).map { line -> calculate(line.split(whitespaceRegex)) }.sum()
}

private val ADDITION = { i: Long, j: Long -> i + j }

private val MULTIPLICATION = { i: Long, j: Long -> i * j }

private val NO_OP = { i: Long, j: Long -> throw IllegalStateException() }

private val OPERATOR_DICTIONARY = mapOf("+" to ADDITION, "*" to MULTIPLICATION)

private fun calculate(tokens: List<String>, acc: Long = 0L, op: (Long, Long) -> Long = ADDITION): Long {
  if (tokens.isEmpty()) return acc
  val token = tokens.first()
  if (token.startsWith('(')) {
    val indexOfClosing = findClosingParenthesisIndex(tokens)
    val innerTokens = tokens.subList(0, indexOfClosing + 1).toMutableList()
    // get rid of parenthesis
    innerTokens[0] = token.drop(1)
    innerTokens[innerTokens.size - 1] = innerTokens.last().dropLast(1)
    val innerAcc = calculate(innerTokens)
    return calculate(tokens.subList(indexOfClosing + 1, tokens.size), op(acc, innerAcc), NO_OP)
  }
  return token.toLongOrNull()?.let { calculate(tokens.drop(1), op(acc, it), NO_OP) }
      ?: calculate(tokens.drop(1), acc, OPERATOR_DICTIONARY.getOrDefault(token, NO_OP))
}

fun findClosingParenthesisIndex(tokens: List<String>, pointer: Int = 0, openedSoFar: Int = 0): Int {
  val currentToken = tokens.getOrElse(pointer) { "" }
  val openedParenthesis = openedSoFar + currentToken.count { it == '(' }
  if (openedParenthesis == 0) return pointer -1
  return findClosingParenthesisIndex(
      tokens, pointer + 1, openedParenthesis - currentToken.count { it == ')' })
}

fun main(args: Array<String>) {
  println(operation("day18.txt".asResource()))
}
