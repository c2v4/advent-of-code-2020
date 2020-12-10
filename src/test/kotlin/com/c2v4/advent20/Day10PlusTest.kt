package com.c2v4.advent20

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class Day10PlusTest : AnnotationSpec() {

  @Test
  fun basicTest() {
    assertThat(
            jolt2(
                "16\n" +
                    "10\n" +
                    "15\n" +
                    "5\n" +
                    "1\n" +
                    "11\n" +
                    "7\n" +
                    "19\n" +
                    "6\n" +
                    "12\n" +
                    "4"))
        .isEqualTo(8)
  }
  
}
