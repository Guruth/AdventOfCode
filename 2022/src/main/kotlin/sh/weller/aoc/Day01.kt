package sh.weller.aoc

object Day01 : SomeDay<String?, Int> {
    override fun partOne(input: List<String?>): Int {
        val elves = mutableListOf<Int>()
        var currentElvCalories: Int = 0

        for (calories in input) {
            if (calories == null) {
                elves.add(currentElvCalories)
                currentElvCalories = 0
            } else {
                currentElvCalories += calories.toInt()
            }
        }
        elves.add(currentElvCalories)

        return elves.max()
    }

    override fun partTwo(input: List<String?>): Int {
        val elves = mutableListOf<Int>()
        var currentElvCalories: Int = 0

        for (calories in input) {
            if (calories == null) {
                elves.add(currentElvCalories)
                currentElvCalories = 0
            } else {
                currentElvCalories += calories.toInt()
            }
        }
        elves.add(currentElvCalories)

        return elves.sortedDescending().take(3).sum()
    }
}