package com.c2v4.advent20

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class Day17PlusTest : AnnotationSpec() {

  @Test
  fun basicTest() {
    assertThat(conway2(".#.\n" + "..#\n" + "###",6)).isEqualTo(848)
  }
}
