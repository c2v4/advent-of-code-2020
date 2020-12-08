package com.c2v4.advent20

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class Day8Test : AnnotationSpec() {

  @Test
  fun basicTest() {
    assertThat(
            handheld(
                "nop +0\n" +
                    "acc +1\n" +
                    "jmp +4\n" +
                    "acc +3\n" +
                    "jmp -3\n" +
                    "acc -99\n" +
                    "acc +1\n" +
                    "jmp -4\n" +
                    "acc +6"))
        .isEqualTo(5)
  }
}
