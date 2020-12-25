package com.c2v4.advent20

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class Day24PlusTest : AnnotationSpec() {

  @Test
  fun basicTest() {
    assertThat(
            lobby2("sesenwnenenewseeswwswswwnenewsewsw\n" +
                    "neeenesenwnwwswnenewnwwsewnenwseswesw\n" +
                    "seswneswswsenwwnwse\n" +
                    "nwnwneseeswswnenewneswwnewseswneseene\n" +
                    "swweswneswnenwsewnwneneseenw\n" +
                    "eesenwseswswnenwswnwnwsewwnwsene\n" +
                    "sewnenenenesenwsewnenwwwse\n" +
                    "wenwwweseeeweswwwnwwe\n" +
                    "wsweesenenewnwwnwsenewsenwwsesesenwne\n" +
                    "neeswseenwwswnwswswnw\n" +
                    "nenwswwsewswnenenewsenwsenwnesesenew\n" +
                    "enewnwewneswsewnwswenweswnenwsenwsw\n" +
                    "sweneswneswneneenwnewenewwneswswnese\n" +
                    "swwesenesewenwneswnwwneseswwne\n" +
                    "enesenwswwswneneswsenwnewswseenwsese\n" +
                    "wnwnesenesenenwwnenwsewesewsesesew\n" +
                    "nenewswnwewswnenesenwnesewesw\n" +
                    "eneswnwswnwsenenwnwnwwseeswneewsenese\n" +
                    "neswnwewnwnwseenwseesewsenwsweewe\n" +
                    "wseweeenwnesenwwwswnew"))
        .isEqualTo(2208)
  }
}
