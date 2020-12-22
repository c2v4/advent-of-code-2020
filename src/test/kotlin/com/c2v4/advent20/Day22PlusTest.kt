package com.c2v4.advent20

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class Day22PlusTest : AnnotationSpec() {

  @Test
  fun basicTest() {
    assertThat(
            combat2(
                "Player 1:\n" +
                    "9\n" +
                    "2\n" +
                    "6\n" +
                    "3\n" +
                    "1\n" +
                    "\n" +
                    "Player 2:\n" +
                    "5\n" +
                    "8\n" +
                    "4\n" +
                    "7\n" +
                    "10"))
        .isEqualTo(291)
  }
}
