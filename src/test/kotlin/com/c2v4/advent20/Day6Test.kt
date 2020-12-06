package com.c2v4.advent20

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class Day6Test : AnnotationSpec() {

  @Test
  fun basicTest() {
    assertThat(
            customs(
                "abc\n" +
                    "\n" +
                    "a\n" +
                    "b\n" +
                    "c\n" +
                    "\n" +
                    "ab\n" +
                    "ac\n" +
                    "\n" +
                    "a\n" +
                    "a\n" +
                    "a\n" +
                    "a\n" +
                    "\n" +
                    "b"))
        .isEqualTo(11)
  }
}
