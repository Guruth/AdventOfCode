package sh.weller.adventofcode.twentytwenty

fun List<Int>.countJoltDiffereneces(startJoltage: Int = 0): Long {
    var currentJoltage = startJoltage
    var oneDifference: Long = 0
    var threeDifference: Long = 1

    this.sorted().forEach { value ->
        val difference = value - currentJoltage
        println("Diff: $difference")
        if (difference >= 1 || difference <= 3) {
            if (difference == 1) {
                oneDifference++
            } else if (difference == 3) {
                threeDifference++
            } else {
                println("Does not fit the difference: $value")
            }
            currentJoltage += difference
        } else {
            println("Can't use this adapter $value")
        }
    }


    println("Cur: $currentJoltage one: $oneDifference three: $threeDifference")

    return oneDifference * threeDifference

}