package com.c2v4.advent20

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class Day15Test : AnnotationSpec() {

  @Test
  fun basicTest() {
    assertThat(
            recitation(
                "0,3,6"))
        .isEqualTo(436)
  }
    @Test
    fun basicTest2() {
        assertThat(
            recitation(
                "0,3,6",30000000))
            .isEqualTo(175594)
    }
}
