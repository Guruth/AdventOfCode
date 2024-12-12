package sh.weller.aoc

object Day03 : SomeDay<List<Char>, Int> {
    override fun partOne(input: List<List<Char>>): Int {
        val gamma = mutableListOf<Char>()
        val epsilon = mutableListOf<Char>()

        repeat(input.first().size) { index ->
            val list = input.map { it[index] }
            val count = list.groupingBy { it }.eachCount()
            val numZero = count['0']!!
            val numOne = count['1']!!

            if (numZero > numOne) {
                gamma.add('0')
                epsilon.add('1')
            } else {
                gamma.add('1')
                epsilon.add('0')
            }
        }

        val gammaNumber = Integer.parseInt(gamma.joinToString(""), 2)
        val epsilonNumber = Integer.parseInt(epsilon.joinToString(""), 2)

        return gammaNumber * epsilonNumber
    }

    override fun partTwo(input: List<List<Char>>): Int {
        var oxygen = input
        var co2 = input

        repeat(input.first().size) { index ->
            if (oxygen.size != 1) {
                val oxygenMostCommonValue = oxygen.findMostCommonValue(index)
                oxygen = oxygen.findOxygen(oxygenMostCommonValue, index)
            }
            if (co2.size != 1) {
                val oxygenMostCommonValue = co2.findMostCommonValue(index)
                co2 = co2.findC02(oxygenMostCommonValue, index)
            }
        }

        val oxygenNumber = Integer.parseInt(oxygen.first().joinToString(""), 2)
        val co2Number = Integer.parseInt(co2.first().joinToString(""), 2)

        return oxygenNumber * co2Number
    }

    private fun List<List<Char>>.findMostCommonValue(pos: Int): MostCommonValue {
        val grouping = map { it[pos] }
            .groupingBy { it }.eachCount()

        return if (grouping['0']!! == grouping['1']!!) {
            MostCommonValue.Equal
        } else if (grouping['0']!! > grouping['1']!!) {
            MostCommonValue.Zero
        } else {
            MostCommonValue.One
        }
    }

    private fun List<List<Char>>.findOxygen(mostCommonValue: MostCommonValue, pos: Int): List<List<Char>> =
        this.filter {
            val currentBit = it[pos]
            return@filter when (mostCommonValue) {
                MostCommonValue.Equal -> currentBit == '1'
                MostCommonValue.One -> currentBit == '1'
                MostCommonValue.Zero -> currentBit == '0'
            }
        }

    private fun List<List<Char>>.findC02(mostCommonValue: MostCommonValue, pos: Int): List<List<Char>> =
        this.filter {
            val currentBit = it[pos]
            return@filter when (mostCommonValue) {
                MostCommonValue.Equal -> currentBit == '0'
                MostCommonValue.One -> currentBit == '0'
                MostCommonValue.Zero -> currentBit == '1'
            }
        }
}


private sealed class MostCommonValue {
    object One : MostCommonValue()
    object Zero : MostCommonValue()
    object Equal : MostCommonValue()
}