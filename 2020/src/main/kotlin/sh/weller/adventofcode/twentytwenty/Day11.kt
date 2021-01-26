package sh.weller.adventofcode.twentytwenty

fun List<List<Char>>.gameOfSeatsPartTwo(): Int {
    var roundCount = 0
    var didChange: Boolean
    var seatRows: List<List<Char>> = this.map { it.toMutableList() }

    do {
        val tmpRows: List<MutableList<Char>> = seatRows.map { it.toMutableList() }
        didChange = false
        seatRows.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnnIndex, seat ->
                val visibleSeats = seatRows.visibleSeats(rowIndex, columnnIndex)

                if (seat == 'L' && visibleSeats.contains('#').not()) {
                    // Everything is empty
                    tmpRows[rowIndex][columnnIndex] = '#'
                    didChange = true
                }
                if (seat == '#' && (visibleSeats.count { it == '#' } >= 5)) {
                    // Too crowded
                    tmpRows[rowIndex][columnnIndex] = 'L'
                    didChange = true
                }
            }
        }

        roundCount++
        seatRows = tmpRows
    } while (didChange)
    return seatRows.map { it.count { seat -> seat == '#' } }.sum()
}

fun List<List<Char>>.visibleSeats(rowIndex: Int, columnIndex: Int): List<Char> {
    val seenSeats = mutableListOf<List<Char?>>()
    var roundCounter = 1

    do {
        val seatsPerRound = listOf(
            // Top Row
            this.getOrNull(rowIndex - roundCounter)?.getOrNull(columnIndex - roundCounter),
            this.getOrNull(rowIndex - roundCounter)?.getOrNull(columnIndex),
            this.getOrNull(rowIndex - roundCounter)?.getOrNull(columnIndex + roundCounter),

            // Right & Left
            this.getOrNull(rowIndex)?.getOrNull(columnIndex + roundCounter),
            this.getOrNull(rowIndex)?.getOrNull(columnIndex - roundCounter),

            // Bottom Row
            this.getOrNull(rowIndex + roundCounter)?.getOrNull(columnIndex + roundCounter),
            this.getOrNull(rowIndex + roundCounter)?.getOrNull(columnIndex),
            this.getOrNull(rowIndex + roundCounter)?.getOrNull(columnIndex - roundCounter),

            )
        seenSeats.add(seatsPerRound)
        roundCounter++
    } while (seatsPerRound.filterNotNull().isNotEmpty())

    return listOfNotNull(
        seenSeats.map { it[0] }.firstSeenSeat(),
        seenSeats.map { it[1] }.firstSeenSeat(),
        seenSeats.map { it[2] }.firstSeenSeat(),
        seenSeats.map { it[3] }.firstSeenSeat(),
        seenSeats.map { it[4] }.firstSeenSeat(),
        seenSeats.map { it[5] }.firstSeenSeat(),
        seenSeats.map { it[6] }.firstSeenSeat(),
        seenSeats.map { it[7] }.firstSeenSeat(),
    )
}

private fun List<Char?>.firstSeenSeat(): Char? {
    var seenSeat: Char? = '.'
    val tmp = this.filterNotNull()
    if (tmp.isNotEmpty()) {
        tmp.forEach {
            if (seenSeat == '.') {
                seenSeat = it
            }
        }
    } else {
        seenSeat = null
    }
    return seenSeat
}


fun List<List<Char>>.gameOfSeats(): Int {
    var roundCount = 0
    var didChange: Boolean

    var seatRows: List<List<Char>> = this.map { it.toMutableList() }

    do {
        val tmpRows: List<MutableList<Char>> = seatRows.map { it.toMutableList() }
        didChange = false
        seatRows.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnnIndex, seat ->
                val adjacentSeats = seatRows.getAdjacentSeats(rowIndex, columnnIndex)
                if (seat == 'L' && adjacentSeats.contains('#').not()) {
                    // Everything is empty
                    tmpRows[rowIndex][columnnIndex] = '#'
                    didChange = true
                }
                if (seat == '#' && (adjacentSeats.count { it == '#' } >= 4)) {
                    // Too crowded
                    tmpRows[rowIndex][columnnIndex] = 'L'
                    didChange = true
                }
            }
        }

        roundCount++
        seatRows = tmpRows
    } while (didChange)
    return seatRows.map { it.count { seat -> seat == '#' } }.sum()
}

fun List<List<Char>>.getAdjacentSeats(rowIndex: Int, columnIndex: Int): List<Char> =
    listOfNotNull(
        // Top Row
        this.getOrNull(rowIndex - 1)?.getOrNull(columnIndex - 1),
        this.getOrNull(rowIndex - 1)?.getOrNull(columnIndex),
        this.getOrNull(rowIndex - 1)?.getOrNull(columnIndex + 1),

        // Left & Right
        this.getOrNull(rowIndex)?.getOrNull(columnIndex - 1),
        this.getOrNull(rowIndex)?.getOrNull(columnIndex + 1),

        // Bottom Row
        this.getOrNull(rowIndex + 1)?.getOrNull(columnIndex - 1),
        this.getOrNull(rowIndex + 1)?.getOrNull(columnIndex),
        this.getOrNull(rowIndex + 1)?.getOrNull(columnIndex + 1),
    )
