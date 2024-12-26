package game

import strategy.AbstractStrategy

class Game(
    val strategy1: AbstractStrategy, val strategy2: AbstractStrategy, val roundNumbers: Int
) {
    private var score1: Int = 0
    private var score2: Int = 0
    private val steps = mutableListOf<Step>()

    fun match(): StrategyGameResult {
        for (i in 1..roundNumbers) {
            val firstStrategyStep = strategy1.move(steps)
            val secondStrategyStep = strategy2.move(steps)
            when {
                firstStrategyStep == secondStrategyStep &&
                        firstStrategyStep == StrategyStep.COOPERATE -> {
                    score1 += StepResultPoint.BOTH_COOPERATE.number
                    score2 += StepResultPoint.BOTH_COOPERATE.number
                }

                firstStrategyStep == secondStrategyStep &&
                        firstStrategyStep == StrategyStep.BETRAYED -> {
                    score1 += StepResultPoint.BOTH_BETRAYED.number
                    score2 += StepResultPoint.BOTH_BETRAYED.number
                }

                firstStrategyStep == StrategyStep.COOPERATE &&
                        secondStrategyStep == StrategyStep.BETRAYED -> {
                    score1 += StepResultPoint.ONE_COOPERATE.number
                    score2 += StepResultPoint.ONE_BETRAYED.number
                }

                firstStrategyStep == StrategyStep.BETRAYED &&
                        secondStrategyStep == StrategyStep.COOPERATE -> {
                    score1 += StepResultPoint.ONE_BETRAYED.number
                    score2 += StepResultPoint.ONE_COOPERATE.number
                }
            }
            steps.add(Step(strategy1, firstStrategyStep, strategy2, secondStrategyStep))
        }
        return StrategyGameResult(strategy1, score1, strategy2, score2)
    }
}