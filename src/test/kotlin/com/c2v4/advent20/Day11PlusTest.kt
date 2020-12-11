package com.c2v4.advent20

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class Day11PlusTest : AnnotationSpec() {

  @Test
  fun basicTest() {
    assertThat(
            seating2(
                "L.LL.LL.LL\n" +
                    "LLLLLLL.LL\n" +
                    "L.L.L..L..\n" +
                    "LLLL.LL.LL\n" +
                    "L.LL.LL.LL\n" +
                    "L.LLLLL.LL\n" +
                    "..L.L.....\n" +
                    "LLLLLLLLLL\n" +
                    "L.LLLLLL.L\n" +
                    "L.LLLLL.LL"))
        .isEqualTo(26)
  }
}
