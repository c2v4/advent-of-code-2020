package com.c2v4.advent20

import com.google.common.collect.Iterables
import java.lang.IllegalArgumentException

fun docking2(input: String) =
    input
        .split(splitRegex)
        .fold("" to mutableMapOf<Long, Long>()) { acc, s ->
          when {
            s.startsWith("mask") -> s.substringAfter("= ") to acc.second
            s.startsWith("mem") -> {

              val baseAddress =
                  s.substringBefore("]").drop(4).toLong() or
                      acc.first.replace("X", "0").toLong(2)
              val addresses = calculateAddresses(baseAddress, acc.first)
              val value = s.substringAfter("= ").toLong()
              addresses.forEach { acc.second[it] = value }
              acc
            }
            else -> throw IllegalArgumentException()
          }
        }
        .second
        .values
        .sum()

fun calculateAddresses(baseAddress: Long, mask: String): Iterable<Long> {
  if(!mask.contains('X')) return listOf(baseAddress)
    val index = mask.indexOf('X')
    val newMask = mask.replaceFirst('X', '0')
    return Iterables.concat(
        calculateAddresses(baseAddress and (1L shl (35-index)).inv(), newMask),
        calculateAddresses(baseAddress or (1L shl (35-index)), newMask)
    )
}

fun main(args: Array<String>) {
  println(docking2("day14.txt".asResource()))
}
