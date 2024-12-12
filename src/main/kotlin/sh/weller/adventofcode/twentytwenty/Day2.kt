package sh.weller.adventofcode.twentytwenty

fun List<String>.validatePasswords(validationType: ValidationType): Int =
    this.map {
        val criteria = it.getCriteria()
        val password = it.getPassword()

        return@map when (validationType) {
            ValidationType.COUNT -> password.validateByCount(criteria)
            ValidationType.POSITION -> password.validateByPosition(criteria)
        }
    }.count { it }

enum class ValidationType {
    COUNT, POSITION
}

private fun String.getCriteria(): Triple<Int, Int, Char> {
    val criteriaPart = this.substringBefore(':', "")
    val lowerBound = criteriaPart.substringBefore('-', "").toInt()
    val upperBound = criteriaPart.substringAfter('-', "").substringBefore(" ", "").toInt()
    val validationChar = criteriaPart.substringAfter(' ', "").first()

    assert(lowerBound > 0) { "lowerBound not valid" }
    assert(upperBound > 0 && upperBound >= lowerBound) { "upperBound not valid" }
    assert(validationChar.isLetter()) { "validationChar not valid" }

    return Triple(lowerBound, upperBound, validationChar)
}

private fun String.getPassword(): String {
    val password = this.substringAfter(':', "")
    assert(password.isNotBlank()) { "password not valid" }
    return password
}

private fun String.validateByCount(criteria: Triple<Int, Int, Char>): Boolean {
    val groupedChars = this.groupBy { it }
    val numOfCriteriaChar = groupedChars[criteria.third]?.count()
    return numOfCriteriaChar != null && numOfCriteriaChar >= criteria.first && numOfCriteriaChar <= criteria.second
}

private fun String.validateByPosition(criteria: Triple<Int, Int, Char>): Boolean {
    return !((this[criteria.first] == criteria.third) && (this[criteria.second] == criteria.third)) && ((this[criteria.first] == criteria.third) || (this[criteria.second] == criteria.third))
}