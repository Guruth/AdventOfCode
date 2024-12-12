package sh.weller.aoc

object Day04 : SomeDay<String, Int> {
    override fun partOne(input: List<String>): Int {
        val drawnNumbers: List<Int> = input.first().getDrawnNumbers()
        var boards: List<List<List<Pair<Int, Boolean>>>> = input.drop(2).toBoards()

        for (drawnNumber in drawnNumbers) {
            boards = boards.map { it.markNumberOnBoard(drawnNumber) }
            for (board in boards) {
                if (board.hasBingo()) {
                    val sumUnmarked = board.flatten().filter { !it.second }.sumOf { it.first }
                    return sumUnmarked * drawnNumber
                }
            }
        }
        return 0
    }

    private fun String.getDrawnNumbers(): List<Int> =
        split(",")
            .map { it.toInt() }

    private fun List<String>.toBoards(): List<List<List<Pair<Int, Boolean>>>> =
        filter { it.isNotBlank() }
            .chunked(5)
            .map { board ->
                board.map { line ->
                    line.split(" ").filter { it.isNotBlank() }
                        .map { number -> number.toInt() to false }
                }
            }

    private fun List<List<Pair<Int, Boolean>>>.markNumberOnBoard(drawnNumber: Int) =
        map { line ->
            line.map { (number, marked) ->
                if (number == drawnNumber) {
                    number to true
                } else {
                    number to marked
                }
            }
        }

    private fun List<List<Pair<Int, Boolean>>>.hasBingo(): Boolean =
        this.checkHorizontalLines() || this.checkVerticalLines()

    private fun List<List<Pair<Int, Boolean>>>.checkHorizontalLines(): Boolean {
        this.forEach { line ->
            if (line.isBingoLine()) {
                return true
            }
        }
        return false
    }

    private fun List<List<Pair<Int, Boolean>>>.checkVerticalLines(): Boolean {
        repeat(this.size) { index ->
            val line = this.map { it[index] }
            if (line.isBingoLine()) {
                return true
            }
        }
        return false
    }

    private fun List<Pair<Int, Boolean>>.isBingoLine(): Boolean =
        map { it.second }
            .reduce { first, second -> first && second }

    override fun partTwo(input: List<String>): Int {
        val drawnNumbers: List<Int> = input.first().getDrawnNumbers()
        var boards: List<List<List<Pair<Int, Boolean>>>> = input.drop(2).toBoards()

        for (drawnNumber in drawnNumbers) {
            if(boards.size > 1) {
                boards = boards
                    .map { it.markNumberOnBoard(drawnNumber) }
                    .filter { it.hasBingo().not() }
            }else{
                boards = boards.map { it.markNumberOnBoard(drawnNumber) }
                if(boards.first().hasBingo()){
                    val sumUnmarked = boards.first().flatten().filter { !it.second }.sumOf { it.first }
                    return sumUnmarked * drawnNumber
                }
            }
        }
        return 0
    }
}
