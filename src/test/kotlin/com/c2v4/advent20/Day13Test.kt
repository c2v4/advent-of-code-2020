package com.c2v4.advent20

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class Day13Test : AnnotationSpec() {

  @Test
  fun basicTest() {
    assertThat(shuttle("939\n" +
      "7,13,x,x,59,x,31,19")).isEqualTo(
      295)
  }
}
