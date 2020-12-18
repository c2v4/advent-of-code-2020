package com.c2v4.advent20

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class Day18Test : AnnotationSpec() {

  @Test
  fun basicTest() {
    assertThat(operation("1 + 2 * 3 + 4 * 5 + 6")).isEqualTo(71)
  }
  @Test
  fun basicTest2() {
    assertThat(operation("1 + (2 * 3) + (4 * (5 + 6))")).isEqualTo(51)
  }
  @Test
  fun basicTest3() {
    assertThat(operation("2 * 3 + (4 * 5)")).isEqualTo(26)
  }
  @Test
  fun basicTest4() {
    assertThat(operation("5 + (8 * 3 + 9 + 3 * 4 * 3)")).isEqualTo(437)
  }
  @Test
  fun basicTest5() {
    assertThat(operation("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))")).isEqualTo(12240)
  }
  @Test
  fun basicTest6() {
    assertThat(operation("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2")).isEqualTo(13632)
  }
}
