package com.c2v4.advent20

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class Day25Test : AnnotationSpec() {

  @Test
  fun basicTest() {
    assertThat(
            combo("5764801\n" +
                    "17807724"))
        .isEqualTo(14897079)
  }
}
