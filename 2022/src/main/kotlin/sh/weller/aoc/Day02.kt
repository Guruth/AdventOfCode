package sh.weller.aoc

object Day02 : SomeDay<Pair<Char, Char>, Int> {
    override fun partOne(input: List<Pair<Char, Char>>): Int =
        input
            .map {
                val (opponent, me) = it
                Shape.fromSymbol(opponent) to Shape.fromSymbol(me)
            }
            .map {
                val (opponent, me) = it
                val outcome = when {
                    me == opponent -> Outcome.Draw

                    me == Shape.Rock && opponent == Shape.Paper -> Outcome.Loss
                    me == Shape.Rock && opponent == Shape.Scissor -> Outcome.Win

                    me == Shape.Paper && opponent == Shape.Scissor -> Outcome.Loss
                    me == Shape.Paper && opponent == Shape.Rock -> Outcome.Win

                    me == Shape.Scissor && opponent == Shape.Paper -> Outcome.Win
                    me == Shape.Scissor && opponent == Shape.Rock -> Outcome.Loss

                    else -> throw IllegalArgumentException("Unknown combination")
                }

                return@map outcome.points + me.points
            }
            .sum()


    override fun partTwo(input: List<Pair<Char, Char>>): Int =
        input
            .map {
                val (opponent, me) = it
                Shape.fromSymbol(opponent) to Shape.fromSymbol(me)
            }
            .map {
                val (opponent, me) = it

                when {
                    // X == Loose
                    me == Shape.Rock -> {
                        val choose = when (opponent) {
                            Shape.Rock -> Shape.Scissor
                            Shape.Paper -> Shape.Rock
                            Shape.Scissor -> Shape.Paper
                        }
                        choose.points + Outcome.Loss.points
                    }
                    // Y == Draw
                    me == Shape.Paper -> {
                        opponent.points + Outcome.Draw.points
                    }
                    // Y == Win
                    me == Shape.Scissor -> {
                        val choose = when (opponent) {
                            Shape.Rock -> Shape.Paper
                            Shape.Paper -> Shape.Scissor
                            Shape.Scissor -> Shape.Rock
                        }
                        choose.points + Outcome.Win.points
                    }

                    else -> throw IllegalArgumentException("Unknown Combination")
                }
            }.sum()

    enum class Outcome(val points: Int) {
        Win(6), Loss(0), Draw(3);
    }

    enum class Shape(val opponentSymbol: Char, val selfSymbol: Char, val points: Int) {
        Rock('A', 'X', 1),
        Paper('B', 'Y', 2),
        Scissor('C', 'Z', 3);

        companion object {
            fun fromSymbol(char: Char): Shape =
                when (char) {
                    'A' -> Rock
                    'X' -> Rock
                    'B' -> Paper
                    'Y' -> Paper
                    'C' -> Scissor
                    'Z' -> Scissor
                    else -> throw IllegalArgumentException("Unknown Shape")
                }

        }
    }
}