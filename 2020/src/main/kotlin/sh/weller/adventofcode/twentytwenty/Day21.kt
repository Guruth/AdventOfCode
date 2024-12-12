package sh.weller.adventofcode.twentytwenty


fun List<String>.day21Part2(): String {
    val allergenIngredientsPairs = this.parseInput()
    val allergenSet = allergenIngredientsPairs.flatMap { it.first }.toSet()

    // All possible ingredients that contain allergens
    val ingredientsThatAppearForEveryAllergen = mutableMapOf<String, MutableList<String>>()
    allergenSet
        .forEach { allergen ->
            val listsWithAllergen =
                allergenIngredientsPairs
                    .filter { it.first.contains(allergen) }
                    .map { it.second }
            val ingredientsThatAreInAllLists = listsWithAllergen.flatten().groupBy { it }
                .filter { it.value.size == listsWithAllergen.size }

            ingredientsThatAppearForEveryAllergen[allergen] =
                ingredientsThatAreInAllLists.map { it.key }.toMutableList()
        }

    val foundAllergenIngredientPairs = mutableListOf<Pair<String, String>>()
    do {
        val notProcessedIngredients = ingredientsThatAppearForEveryAllergen
            .filter { foo ->
                foundAllergenIngredientPairs.map { it.first }.contains(foo.key).not()
            }
            .mapValues {
                it.value.filter { possibleIngredient ->
                    foundAllergenIngredientPairs.map { foundIngredient -> foundIngredient.second }
                        .contains(possibleIngredient).not()
                }
            }

        notProcessedIngredients
            .filter { it.value.size == 1 }
            .forEach {
                foundAllergenIngredientPairs.add(Pair(it.key, it.value.single()))
            }
    } while (ingredientsThatAppearForEveryAllergen.size != foundAllergenIngredientPairs.size)

    return foundAllergenIngredientPairs.sortedBy { it.first }.map { it.second }.joinToString(",")
}

fun List<String>.day21Part1(): Int {
    val allergenIngredientsPairs = this.parseInput()
    val allergenSet = allergenIngredientsPairs.flatMap { it.first }.toSet()

    // All possible ingredients that contain allergens
    val ingredientsThatAppearForEveryAllergen = mutableMapOf<String, MutableList<String>>()
    allergenSet
        .forEach { allergen ->
            val listsWithAllergen =
                allergenIngredientsPairs
                    .filter { it.first.contains(allergen) }
                    .map { it.second }
            val ingredientsThatAreInAllLists = listsWithAllergen.flatten().groupBy { it }
                .filter { it.value.size == listsWithAllergen.size }

            ingredientsThatAppearForEveryAllergen[allergen] =
                ingredientsThatAreInAllLists.map { it.key }.toMutableList()
        }

    val foundAllergenIngredientPairs = mutableListOf<Pair<String, String>>()
    do {
        val notProcessedIngredients = ingredientsThatAppearForEveryAllergen
            .filter { foo ->
                foundAllergenIngredientPairs.map { it.first }.contains(foo.key).not()
            }
            .mapValues {
                it.value.filter { possibleIngredient ->
                    foundAllergenIngredientPairs.map { foundIngredient -> foundIngredient.second }
                        .contains(possibleIngredient).not()
                }
            }

        notProcessedIngredients
            .filter { it.value.size == 1 }
            .forEach {
                foundAllergenIngredientPairs.add(Pair(it.key, it.value.single()))
            }
    } while (ingredientsThatAppearForEveryAllergen.size != foundAllergenIngredientPairs.size)


    return allergenIngredientsPairs.flatMap { it.second }
        .filter { foundAllergenIngredientPairs.map { it.second }.contains(it).not() }
        .size
}


private fun List<String>.parseInput(): List<Pair<List<String>, List<String>>> =
    this
        .map { line ->
            val ingredients = line.split("(")[0].split(" ").filter { it.isNotBlank() }.map { it.trim() }
            val allergens =
                line.split("(")[1].dropLast(1).drop(8).split(",").filter { it.isNotBlank() }.map { it.trim() }

            return@map allergens to ingredients
        }