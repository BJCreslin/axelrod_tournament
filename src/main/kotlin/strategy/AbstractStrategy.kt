package strategy

import game.Step
import game.StrategyStep

abstract class AbstractStrategy {
    abstract fun getName(): String
    abstract fun move(steps: List<Step>): StrategyStep

}