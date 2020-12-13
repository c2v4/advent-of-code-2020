package com.c2v4.advent20

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class Day13PlusTest : AnnotationSpec() {

  @Test
  fun basicTest() {
    assertThat(shuttle2("939\n" +
      "7,13,x,x,59,x,31,19")).isEqualTo(
      1068781)
  }

  @Test
  fun basicTest2() {
    assertThat(shuttle2("939\n" +
            "17,x,13,19")).isEqualTo(
      3417)
  }

  @Test
  fun basicTest3() {
    assertThat(shuttle2("939\n" +
            "67,7,59,61")).isEqualTo(
      754018)
  }
  @Test
  fun basicTest4() {
    assertThat(shuttle2("939\n" +
            "67,x,7,59,61")).isEqualTo(
      779210)
  }

  @Test
  fun basicTest5() {
    assertThat(shuttle2("939\n" +
            "67,7,x,59,61")).isEqualTo(
      1261476)
  }

  @Test
  fun basi3Test5() {
    assertThat(shuttle2("939\n" +
            "1789,37,47,1889")).isEqualTo(
      1202161486)
  }

}
