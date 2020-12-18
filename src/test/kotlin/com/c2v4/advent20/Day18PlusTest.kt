package com.c2v4.advent20

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class Day18PlusTest : AnnotationSpec() {

  @Test
  fun basicTest() {
    assertThat(operation2("1 + 2 * 3 + 4 * 5 + 6")).isEqualTo(231)
  }
  @Test
  fun basicTest2() {
    assertThat(operation2("1 + (2 * 3) + (4 * (5 + 6))")).isEqualTo(51)
  }
  @Test
  fun basicTest3() {
    assertThat(operation2("2 * 3 + (4 * 5)")).isEqualTo(46)
  }
  @Test
  fun basicTest4() {
    assertThat(operation2("5 + (8 * 3 + 9 + 3 * 4 * 3)")).isEqualTo(1445)
  }
  @Test
  fun basicTest5() {
    assertThat(operation2("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))")).isEqualTo(669060)
  }
  @Test
  fun basicTest6() {
    assertThat(operation2("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2")).isEqualTo(23340)
  }
}
