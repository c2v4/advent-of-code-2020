package com.c2v4.advent20

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class Day2PlusTest : AnnotationSpec() {

  @Test
  fun basicTest() {
    assertThat(password2("1-3 a: abcde\n"
            + "1-3 b: cdefg\n"
            + "2-9 c: ccccccccc")).isEqualTo(1)
  }
}
