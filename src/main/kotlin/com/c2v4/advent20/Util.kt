package com.c2v4.advent20


import java.util.*
import kotlin.math.abs

fun String.asResource() = Thread.currentThread().contextClassLoader.getResource(this).readText()

val EOL = Regex("\\r?\\n")

fun prepareComputerInput(input: String) = input.split(',').map { it.toInt() }.toIntArray()

fun String.asComputerinput() = prepareComputerInput(this)
val PARAGRAPH = Regex("\\r?\\n\\r?\\n")

fun <T> LinkedList<T>.rotate(num:Int){
    if (num == 0) return
    if (num > 0) {
        repeat((0 until num).count()) {
            val t = this.removeLast()
            this.addFirst(t)
        }
    } else {
        repeat((0 until (abs(num) -1)).count()) {
            val t = this.remove()
            this.addLast(t)
        }
    }
}