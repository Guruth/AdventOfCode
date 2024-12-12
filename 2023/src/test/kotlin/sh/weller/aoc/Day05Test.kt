package sh.weller.aoc

class Day05Test : SomeDayTest<Pair<SeedList, TypeMappingList>, Long>(5, Day05) {
    override fun List<String>.mapData(): List<Pair<SeedList, TypeMappingList>> {
        val seeds = this.first().split(":").last().split(" ").filterNot { it.isBlank() }
            .map { it.toLong() }

        var currentMap = ""
        val mappings = mutableMapOf<String, MutableList<Triple<Long, Long, Long>>>()

        this.drop(1)
            .forEach { line ->
                if (line.isBlank()) {
                    return@forEach
                }

                if (line.contains("map")) {
                    currentMap = line.split(" ").first()
                    return@forEach
                }

                val mapping = line.split(" ").filterNot { it.isBlank() }.map { it.toLong() }
                val source = mapping[0]
                val destination = mapping[1]
                val range = mapping[2]

                val mappingList = mappings.getOrDefault(currentMap, mutableListOf())
                mappingList.add(Triple(source, destination, range))
                mappings[currentMap] = mappingList
            }

        return listOf(seeds to mappings.values.toList())
    }

    override val resultTest1: Long = 35
    override val resultTest2: Long = 46

}

