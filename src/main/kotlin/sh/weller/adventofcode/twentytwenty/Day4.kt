package sh.weller.adventofcode.twentytwenty

fun List<String>.validatePassportsPartTwo(expectedFieldKeys: List<String>): Int {
    val passports = parsePassports()
//    println("Possible Passports: ${passports.filter { it.size >= 7 }.size}")

    val validPassports = passports
        .filter {
            it.map { it.first }
                .containsAll(expectedFieldKeys)
        }
        .filter {
            val isValid = it.isValidPassportPartTwo()
//            if (!isValid) {
//                println(it)
//            }
            return@filter isValid
        }

    return validPassports.size
}

fun List<Pair<String, String>>.isValidPassportPartTwo(): Boolean {
    val byr = this.firstOrNull { it.first == "byr" }?.let {
        it.second.toInt() in 1920..2002
    } ?: false
    val iyr = this.firstOrNull { it.first == "iyr" }?.let { it.second.toInt() in 2010..2020 } ?: false
    val eyr = this.firstOrNull { it.first == "eyr" }?.let { it.second.toInt() in 2020..2030 } ?: false
    val hgt = this.firstOrNull { it.first == "hgt" }?.let {
        return@let if (it.second.contains("cm")) {
            it.second.removeSuffix("cm").toInt() in 150..193
        } else if (it.second.contains("in")) {
            it.second.removeSuffix("in").toInt() in 59..76
        } else {
            false
        }
    } ?: false
    val hcl = this.firstOrNull { it.first == "hcl" }?.let {
        return@let if (it.second.startsWith("#")) {
            val hex = it.second.removePrefix("#").toCharArray()
            return@let if (hex.size == 6) {
                val filtered = hex.filter { char ->
                    char in listOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')
                }
                return@let filtered.size == 6
            } else {
                false
            }
        } else {
            false
        }
    } ?: false
    val ecl = this.firstOrNull { it.first == "ecl" }?.let {
        it.second in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
    } ?: false
    val pid = this.firstOrNull { it.first == "pid" }?.let { pidPair ->
        val pidValue = pidPair.second
        val hasLength = pidValue.toCharArray().size == 9
        return@let if (hasLength) {
            pidValue.toIntOrNull() != null
        } else {
            false
        }
    } ?: false

//    if (!hgt) {
//        println("hgt: ${this.filter { it.first == "hgt" }}")
//    }

    return byr && iyr && eyr && hgt && hcl && ecl && pid
}

fun List<String>.validatePassports(expectedFieldKeys: List<String>): Int {
    val passports = parsePassports()
//    println("Possible Passports: ${passports.filter { it.size >= 7 }.size}")

    val validPassports = passports.filter {
        it.map { it.first }
            .containsAll(expectedFieldKeys)
    }

    return validPassports.size
}

private fun List<String>.parsePassports(): List<List<Pair<String, String>>> {
    val passportList = mutableListOf<List<Pair<String, String>>>()
    var tmpList = mutableListOf<Pair<String, String>>()
//    println("Blank Lines ${this.filter { it.isBlank() }.count()}")

    this.forEachIndexed { i, line ->
        if (line.isBlank()) {
            passportList.add(tmpList)
            tmpList = mutableListOf()
        }
        val splitLine = line.split(" ")
        splitLine.forEach { field ->
            if (field.isNotBlank()) {
                val splitField = field.split(":")
                tmpList.add(Pair(splitField[0], splitField[1]))
            }
        }
        if ((i + 1) == this.size) {
            passportList.add(tmpList)
        }
    }
//    println("Found passports: ${passportList.size}")

    return passportList
}


