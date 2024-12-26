package game

import strategy.AbstractStrategy

data class StrategyGameResult(
    val firstStrategy: AbstractStrategy, val firstPoint: Int,
    val secondStrategy: AbstractStrategy, val secondPoint: Int
)
