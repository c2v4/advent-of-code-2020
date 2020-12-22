package com.c2v4.advent20

fun String.asResource() = Thread.currentThread().contextClassLoader.getResource(this).readText()

val EOL = Regex("\\r?\\n")

fun prepareComputerInput(input: String) = input.split(',').map { it.toInt() }.toIntArray()

fun String.asComputerinput() = prepareComputerInput(this)
val PARAGRAPH = Regex("\\r?\\n\\r?\\n")