package sh.weller.aoc

import kotlin.jvm.optionals.getOrElse
import kotlin.math.min
import kotlin.streams.asStream
import kotlin.time.measureTimedValue

object Day05 : SomeDay<Pair<SeedList, TypeMappingList>, Long> {
    override fun partOne(input: List<Pair<SeedList, TypeMappingList>>): Long {
        val (seeds, typeMappingList) = input.first()

        return seeds
            .map { findMapping(it, typeMappingList) }
            .min()
    }

    override fun partTwo(input: List<Pair<SeedList, TypeMappingList>>): Long {
        val (seeds, typeMappingList) = input.first()
        val seedRanges = seeds.chunked(2)

        val (result, duration) = measureTimedValue {
            sequence {
                for (seedRange in seedRanges) {
                    val start = seedRange.first()
                    val range = seedRange.last()

                    var current = start
                    while (current <= start + range) {
                        yield(current)
                        current += 1
                    }
                }
            }
                .asStream()
                .parallel()
                .map { findMapping(it, typeMappingList) }
                .reduce { currentMin, newValue ->
                    min(currentMin, newValue)
                }
                .getOrElse { 0L }
        }

        println("Took $duration")

        return result
    }

    private fun findMapping(seed: Long, typeMappingList: TypeMappingList): Long {
        var currentMapping = seed

        for (mappingList in typeMappingList) {
            var destinationMapping = -1L

            for (mapping in mappingList) {
                val (destination, source, range) = mapping

                val distance = currentMapping - source
                if (distance in 0..range) {
                    destinationMapping = destination + distance
                    break
                }
            }
            if (destinationMapping != -1L) {
                currentMapping = destinationMapping
            }
        }
        return currentMapping
    }

}

typealias SeedList = List<Long>
typealias Mapping = Triple<Long, Long, Long>
typealias TypeMappings = List<Mapping>
typealias TypeMappingList = List<TypeMappings>