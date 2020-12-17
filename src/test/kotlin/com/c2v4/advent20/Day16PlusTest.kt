package com.c2v4.advent20

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class Day16PlusTest : AnnotationSpec() {

  @Test
  fun basicTest() {
    assertThat(
            ticket2(
                "class: 0-1 or 4-19\n" +
                    "row: 0-5 or 8-19\n" +
                    "seat: 0-13 or 16-19\n" +
                    "\n" +
                    "your ticket:\n" +
                    "11,12,13\n" +
                    "\n" +
                    "nearby tickets:\n" +
                    "3,9,18\n" +
                    "15,1,5\n" +
                    "5,14,9","cl"))
        .isEqualTo(12)
  }
}
