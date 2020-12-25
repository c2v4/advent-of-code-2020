package com.c2v4.advent20

fun combo(input: String, subject: Long = 7L) =
    input.split(EOL).map { it.toLong() }.let { keys ->
        val loop = encryptionSequence(subject).find { it.second == keys[0] }!!.first
        encryptionSequence(keys[1]).elementAt(loop).second
    }

private fun encryptionSequence(subject: Long) = generateSequence(0 to 1L) { (index, value) ->
    index + 1 to ((value * subject) % 20201227)
}


fun main(args: Array<String>) {
    println(
        combo(
            "8184785\n" +
                    "5293040"
        )
    )
}
