package com.c2v4.advent20

fun jigsaw(input: String) =
    input
        .split(PARAGRAPH)
        .map { tile ->
          val split = tile.split(EOL)
          val id = split.first().substringAfter(' ').dropLast(1).toLong()
          val grid = split.drop(1).map { line -> line.map { it == '#' } }
          Tile(id, grid)
        }
        .let { tiles ->
          tiles.filter { tile ->
            tiles.count { currentTile ->
              currentTile != tile &&
                  currentTile.getBorders().any { border ->
                    tile.getBorders().any { it == border } ||
                        tile.getBorders().map { it.reversed() }.any { it == border }
                  }
            } == 2
          }
        }.map { it.id }.reduce { acc, l -> acc*l }

data class Tile(val id: Long, val grid: List<List<Boolean>>)

private fun Tile.getBorders() =
    listOf(grid.first(), grid.last(), grid.map { it.last() }, grid.map { it.first() })

fun main(args: Array<String>) {
  println(jigsaw("day20.txt".asResource()))
}
