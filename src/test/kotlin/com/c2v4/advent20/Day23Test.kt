package com.c2v4.advent20

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class Day23Test : AnnotationSpec() {

  @Test
  fun basicTest() {
    assertThat(
            cups(
                "389125467",10))
        .isEqualTo("92658374")
  }

    @Test
    fun basicTest2() {
        assertThat(
            cups(
                "389125467",100))
            .isEqualTo("67384529")
    }
}
