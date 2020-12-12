package com.c2v4.advent20

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class Day12Test : AnnotationSpec() {

  @Test
  fun basicTest() {
    assertThat(rain("F10\n" + "N3\n" + "F7\n" + "R90\n" + "F11")).isEqualTo(
      25)
  }
}
