package com.c2v4.advent20

fun monster2(input: String) =
    input.split(Regex("\\r?\\n\\r?\\n")).map { it.split(EOL) }.let { sections ->
      val rawInputRules =
          sections[0]
              .map {
                val split = it.split(": ")
                split[0].toInt() to split[1]
              }
              .toMap()
              .toMutableMap()
      rawInputRules[8] = "42 | 42 8"
      rawInputRules[11] = "42 31 | 42 11 31"
      val stringsToCheck = sections[1]
      val rules = getRules(rawInputRules)
      stringsToCheck.count { rules[0]!!.evaluate(it).first }
    }

private fun getRules(rawInputRules: Map<Int, String>): Map<Int, Rule> {

  val tempMap = mutableMapOf<Int, Rule>()

  rawInputRules.forEach { (index, ruleString) ->
    val rule: Rule =
        when {
          ruleString.contains('"') ->
              Rule(tempMap) { s ->
                (s.isNotEmpty() && s.first() == ruleString[1]) to s.drop(1)
              } // there are no more than one letter base rules
          ruleString.contains('|') ->
              ruleString.split(" | ").map { processJoinRule(it, tempMap) }
                  .reduce {
              acc,
              rule ->
                acc.split(rule)
              }
          else -> processJoinRule(ruleString, tempMap)
        }
    tempMap[index] = rule
  }
  return tempMap
}

private fun processJoinRule(
    ruleString: String,
    tempMap: MutableMap<Int, Rule>
) = ruleString.split(" ").fold(Rule(tempMap) { s -> true to s }) { acc, s -> acc.join(s.toInt()) }

class Rule(private val ruleMap: Map<Int, Rule>, val function: (String) -> Pair<Boolean, String>) {

  private val empty = false to ""

  fun join(int: Int) =
      Rule(ruleMap) {
        val (result, tail) = evaluate(it)
        val next =
            ruleMap[int] ?: throw IllegalArgumentException("Nu such rule:$int in the ruleset")
        if (result) next.evaluate(tail) else empty
      }

  fun split(next: Rule) =
      Rule(ruleMap) {
        val (result, tail) = evaluate(it)
        if (result) result to tail else next.evaluate(it)
      }

  fun evaluate(string: String): Pair<Boolean, String> = function(string)
}

fun main(args: Array<String>) {
  println(monster2("day19.txt".asResource()))
}
