package com.c2v4.advent20

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class Day23PlusTest : AnnotationSpec() {

  @Test
  fun basicTest() {
    assertThat(
            cups2(
                "389125467",10_000_000,1_000_000))
        .isEqualTo(149245887792L)
  }
    @Test
    fun basicTest2() {
        assertThat(
            cups2(
                "389125467",10,9))
            .isEqualTo(18)
    }


}
