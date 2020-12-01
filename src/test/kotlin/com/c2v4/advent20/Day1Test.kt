package com.c2v4.advent20

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions

class Day1Test : AnnotationSpec() {

  @Test
  fun day1test() {
    Assertions.assertThat(reportRepair("1721\n" +
            "979\n" +
            "366\n" +
            "299\n" +
            "675\n" +
            "1456"))
        .isEqualTo(514579)
  }
}
