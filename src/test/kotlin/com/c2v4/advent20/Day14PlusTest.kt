package com.c2v4.advent20

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class Day14PlusTest : AnnotationSpec() {

  @Test
  fun basicTest() {
    assertThat(
            docking2(
                "mask = 000000000000000000000000000000X1001X\n" +
                    "mem[42] = 100\n" +
                    "mask = 00000000000000000000000000000000X0XX\n" +
                    "mem[26] = 1"))
        .isEqualTo(208)
  }
}
