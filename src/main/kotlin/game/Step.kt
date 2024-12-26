package game

import strategy.AbstractStrategy

data class Step(
    val firstStrategy: AbstractStrategy, val firstStrategyStep: StrategyStep,
    val secondStrategy: AbstractStrategy, val secondStrategyStep: StrategyStep
) {
    fun getOpponentMove(myStrategy: AbstractStrategy): StrategyStep {
        return if (myStrategy == firstStrategy) secondStrategyStep else firstStrategyStep
    }
}