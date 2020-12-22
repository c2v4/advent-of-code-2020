package com.c2v4.advent20

fun jigsaw2(input: String) =
    input
        .split(PARAGRAPH)
        .map { tile ->
          val split = tile.split(EOL)
          val id = split.first().substringAfter(' ').dropLast(1).toLong()
          val grid = split.drop(1).map { line -> line.map { it == '#' } }
          Tile(id, grid)
        }
        .let { tiles ->
          // find correct orientation and position
          val notPositionedYet = tiles.drop(1).toMutableSet()
          val completeGrid: Array<Array<Tile?>> = Array(7) { Array(7) { null as Tile? } }
          completeGrid[2][2] = tiles.first()
          val awaitingSides =
              tiles
                  .first()
                  .getBorders()
                  .map {
                    val position = Point2(2, 2) + it.key.transformerPoint
                    position to FreeSpot(position, mapOf(it.key.getOpposite() to it.value))
                  }
                  .toMap()
                  .toMutableMap()
          while (notPositionedYet.isNotEmpty()) {
            val (foundTile, foundSpot) = notPositionedYet
                .asSequence()
                .flatMap { notPositioned ->
                  notPositioned.combinations().flatMap { tile ->
                    awaitingSides.values.map { tile to it }
                  }
                }
                .find { (tile, freeSpot) ->
                  val borders = tile.getBorders()
                  freeSpot.fixed.all { fixed -> fixed.value == borders[fixed.key] }
                }
                ?: throw IllegalStateException("Cannot match more sides")
            notPositionedYet.removeIf { it.id == foundTile.id }
            completeGrid[foundSpot.position.x][foundSpot.position.y] = foundTile
            awaitingSides.remove(foundSpot.position)
            foundTile
                .getBorders()
                .map { foundSpot.position + it.key.transformerPoint to it }
                .filter { completeGrid[it.first.x][it.first.y] == null }
                .forEach { (position, border) ->
                  awaitingSides.merge(
                      position,
                      FreeSpot(position, mapOf(border.key.getOpposite() to border.value))) { a, b ->
                    FreeSpot(a.position, a.fixed.plus(b.fixed))
                  }
                }
          }
          completeGrid
        }
        .let {
        // combine image
        grid ->
          val finalGrid =
              grid.asSequence()
                  .filter { arrayOfTiles -> arrayOfTiles.any { it != null } }
                  .map { arrayOfTiles -> arrayOfTiles.filterNotNull().map { it.grid } }
                  .map {
                    it.map { it.drop(1).dropLast(1).map { it.drop(1).dropLast(1) }.reversed() }
                  }
                  .toList()
          //          val stringMap =
          //              finalGrid.map {
          //                it.map {
          //                  it.joinToString("\n") { it.map { if (it) '#' else '.'
          // }.joinToString("") }
          //                }
          //              }
          //          println(stringMap)
          val image =
              finalGrid.map { it.reduce { acc, list -> acc + list } }.reduce { acc, list ->
                list.mapIndexed { index, innerList -> acc[index] + innerList }
              }
          //          val joinToString =
          //              image.joinToString("\n") { it.map { if (it) '#' else '.'
          // }.joinToString("") }
          //          println(joinToString)
          image
        }
        .let {
        // find monsters
        image ->
          val baseValue = image.sumBy { it.count { it } }
          val imageCombinations = Tile(0L, image).combinations().map { it.grid }
          imageCombinations
              .map { currentImage ->
                currentImage.windowed(MONSTER.size).sumBy { windowed ->
                  windowed.map { it.windowed(MONSTER[0].size) }.let { chunks ->
                    val unwrapped = chunks[0].indices.map { i -> chunks.map { it[i] } }
                    unwrapped.count { partialImage ->
                      MONSTER.indices.all { i ->
                        MONSTER[i].mapIndexed { index, b -> !b || partialImage[i][index] }.all {
                          it
                        }
                      }
                    }
                  }
                }
              }
              .maxOrNull()
              ?.let { baseValue - it * MONSTER_COUNT }
              ?: 0
        }

private val MONSTER =
    ("                  # \n" + "#    ##    ##    ###\n" + " #  #  #  #  #  #   ").split(EOL).map {
    line ->
      line.map { it == '#' }
    }

private val MONSTER_COUNT = MONSTER.sumBy { it.count { it } }

private fun Tile.combinations() = ALL_COMBINATION_TRANSFORMERS.map { it(this) }

private data class FreeSpot(val position: Point2, val fixed: Map<Side, List<Boolean>>)

private data class Point2(val x: Int, val y: Int)

private operator fun Point2.plus(other: Point2) = Point2(this.x + other.x, this.y + other.y)

fun List<Boolean>.withFlipped() = listOf(this, this.reversed())

private fun Tile.getBorders() =
    mapOf(
        Side.TOP to grid.first(),
        Side.BOTTOM to grid.last(),
        Side.LEFT to grid.map { it.first() },
        Side.RIGHT to grid.map { it.last() })

private enum class Side(x: Int, y: Int) {
  TOP(0, 1),
  RIGHT(1, 0),
  BOTTOM(0, -1),
  LEFT(-1, 0);

  val transformerPoint = Point2(x, y)

  fun getOpposite() =
      when (this) {
        TOP -> BOTTOM
        RIGHT -> LEFT
        BOTTOM -> TOP
        LEFT -> RIGHT
      }
}

val TRANSPOSE: Transformer =
    { tile -> Tile(tile.id, tile.grid.indices.map { i -> tile.grid.map { it[i] } }) }

val FLIP: Transformer = { tile -> Tile(tile.id, tile.grid.map { it.reversed() }) }

val ROTATE: Transformer = TRANSPOSE + FLIP

val ALL_COMBINATION_TRANSFORMERS =
    setOf(
        { it },
        ROTATE,
        ROTATE + ROTATE,
        ROTATE + ROTATE + ROTATE,
        FLIP,
        FLIP + ROTATE,
        FLIP + ROTATE + ROTATE,
        FLIP + ROTATE + ROTATE + ROTATE)

typealias Transformer = (Tile) -> Tile

operator fun Transformer.plus(other: Transformer) = { tile: Tile -> other(this(tile)) }

fun main(args: Array<String>) {
  println(jigsaw2("day20.txt".asResource()))
}
