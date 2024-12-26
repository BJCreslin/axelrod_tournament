package strategy

import game.Step
import game.StrategyStep

class RandomStrategy : AbstractStrategy() {
    override fun getName(): String {
        return "RandomStrategy"
    }

    override fun move(steps: List<Step>): StrategyStep {
        return StrategyStep.entries.toTypedArray().random()
    }
}