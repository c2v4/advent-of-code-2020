package com.c2v4.advent20

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class Day17Test : AnnotationSpec() {

  @Test
  fun basicTest() {
    assertThat(conway(".#.\n" + "..#\n" + "###",6)).isEqualTo(112)
  }
}
