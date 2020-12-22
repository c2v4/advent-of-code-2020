package com.c2v4.advent20

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class Day21PlusTest : AnnotationSpec() {

  @Test
  fun basicTest() {
    assertThat(
            allergen2(
                "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)\n" +
                    "trh fvjkl sbzzf mxmxvkd (contains dairy)\n" +
                    "sqjhc fvjkl (contains soy)\n" +
                    "sqjhc mxmxvkd sbzzf (contains fish)"))
        .isEqualTo("mxmxvkd,sqjhc,fvjkl")
  }
}
