package sh.weller.aoc

object Day09 : SomeDay<Long> {

    override val day: Int = 9

    override fun partOne(input: List<String>): Long {
        val disk = input.first()
            .toCharArray()
            .mapIndexed { index, i ->
                if (index % 2 == 0) {
                    val id = index / 2
                    return@mapIndexed (0..<i.digitToInt()).map { id }
                } else {
                    return@mapIndexed (0..<i.digitToInt()).map { -1 }
                }
            }
            .flatten()
            .toMutableList()

        var fIndex = disk.indexOfFirst { it == -1 }
        var rIndex = disk.indexOfLast { it != -1 }
        while (fIndex < rIndex) {
            disk[fIndex] = disk[rIndex]
            disk[rIndex] = -1
            fIndex = disk.indexOfFirst { it == -1 }
            rIndex = disk.indexOfLast { it != -1 }
        }

        return disk.filter { it != -1 }
            .mapIndexed { index, i -> index * i.toLong() }
            .sum()
    }

    override fun partTwo(input: List<String>): Long {
        val disk = input.first()
            .toCharArray().map { it.digitToInt() }
            .mapIndexedNotNull { index, blockSize ->
                if (blockSize != 0) {
                    val id = if (index % 2 == 0) {
                        index / 2
                    } else {
                        -1
                    }
                    blockSize to id
                } else null
            }
            .toMutableList()

        var largestId = disk.maxOf { it.second }
        var largestIdBlockIndex = disk.indexOfLast { (_, id) -> id == largestId }
        var largestIdBlock = disk[largestIdBlockIndex]
        largestId--

        var fIndex = disk.indexOfFirst { (blockSize, id) -> id == -1 && blockSize >= largestIdBlock.first }
        var fBlock = disk.getOrNull(fIndex)

        while (largestId >= 0) {
            if (fBlock != null && fIndex < largestIdBlockIndex) {
                val blockSizeDiff = fBlock.first - largestIdBlock.first
                if (blockSizeDiff == 0) {
                    disk[fIndex] = largestIdBlock
                    disk[largestIdBlockIndex] = fBlock
                } else if (blockSizeDiff > 0) {
                    disk[fIndex] = largestIdBlock
                    disk[largestIdBlockIndex] = largestIdBlock.first to -1
                    disk.add(fIndex + 1, blockSizeDiff to -1)
                }
            }
            largestIdBlockIndex = disk.indexOfLast { (_, id) -> id == largestId }
            largestIdBlock = disk[largestIdBlockIndex]
            largestId--

            fIndex = disk.indexOfFirst { (blockSize, id) -> id == -1 && blockSize >= largestIdBlock.first }
            fBlock = disk.getOrNull(fIndex)

        }

        printDiskLayout(disk)

        return disk
            .map { block -> (0..<block.first).map { block.second } }.flatten().map {
                if (it == -1) {
                    0
                } else {
                    it
                }
            }

            .mapIndexed { index, i -> index * i.toLong() }
            .sum()
    }

    private fun printDiskLayout(disk: List<Pair<Int, Int>>) {
        val diskLayout = disk
            .map { block -> (0..<block.first).map { block.second } }.flatten().map {
                if (it == -1) {
                    "."
                } else {
                    "$it"
                }
            }.joinToString("")

        println(diskLayout)
    }
}
