package com.c2v4.advent20

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class Day16Test : AnnotationSpec() {

  @Test
  fun basicTest() {
    assertThat(
            ticket(
                "class: 1-3 or 5-7\n" +
                    "row: 6-11 or 33-44\n" +
                    "seat: 13-40 or 45-50\n" +
                    "\n" +
                    "your ticket:\n" +
                    "7,1,14\n" +
                    "\n" +
                    "nearby tickets:\n" +
                    "7,3,47\n" +
                    "40,4,50\n" +
                    "55,2,20\n" +
                    "38,6,12"))
        .isEqualTo(71)
  }

}
