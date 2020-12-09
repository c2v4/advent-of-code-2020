package com.c2v4.advent20

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class Day9Test : AnnotationSpec() {

  @Test
  fun basicTest() {
    assertThat(
            encoding(
                "35\n" +
                    "20\n" +
                    "15\n" +
                    "25\n" +
                    "47\n" +
                    "40\n" +
                    "62\n" +
                    "55\n" +
                    "65\n" +
                    "95\n" +
                    "102\n" +
                    "117\n" +
                    "150\n" +
                    "182\n" +
                    "127\n" +
                    "219\n" +
                    "299\n" +
                    "277\n" +
                    "309\n" +
                    "576",5))
        .isEqualTo(127)
  }
}
