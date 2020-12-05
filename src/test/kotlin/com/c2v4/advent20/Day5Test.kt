package com.c2v4.advent20

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class Day5Test : AnnotationSpec() {

  @Test
  fun basicTest() {
    assertThat(
            binary(
                "BFFFBBFRRR\n" +
                    "FFFBBBFRRR\n" +
                    "BBFFBBFRLL"))
        .isEqualTo(820)
  }
}
