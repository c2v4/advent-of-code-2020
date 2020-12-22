package com.c2v4.advent20

import arrow.syntax.function.memoize
import com.google.common.collect.Sets

fun monster(input: String) =
    input.split(Regex("\\r?\\n\\r?\\n")).map { it.split(EOL) }.let { sections ->
      val correctStrings =
          getRuleCalculatingFunction(
              sections[0]
                  .map {
                    val split = it.split(": ")
                    split[0].toInt() to split[1]
                  }
                  .toMap())(0)
      val stringsToCheck = sections[1]
      stringsToCheck.count { correctStrings.contains(it) }
    }

private fun getRuleCalculatingFunction(rawInputRules: Map<Int, String>): (Int) -> Set<String> {
  var calculatePossibleStringsForRule: (Int) -> Set<String> = { emptySet() }
  calculatePossibleStringsForRule =
      { rule: Int ->
            val ruleString =
                rawInputRules[rule]
                    ?: throw IllegalArgumentException("Nu such rule:$rule in the ruleset")
            when {
              ruleString.contains('"') ->
                  setOf(ruleString[1].toString()) // there are no more than one letter base rules
              ruleString.contains('|') ->
                  ruleString
                      .split(" | ")
                      .flatMap {
                        processSimpleRule(it,calculatePossibleStringsForRule)
                      }
                      .toSet()
              else ->
                  processSimpleRule(ruleString, calculatePossibleStringsForRule)
            }
          }.memoize()

  return calculatePossibleStringsForRule
}

private fun processSimpleRule(
    ruleString: String,
    calculatePossibleStringsForRule: (Int) -> Set<String>
): Set<String> {
    return ruleString
        .split(" ")
        .map { calculatePossibleStringsForRule(it.toInt()) }
        .let { sets -> Sets.cartesianProduct(sets).map { it.joinToString("") } }
        .toSet()
}

fun main(args: Array<String>) {
  println(monster("day19.txt".asResource()))
}
