package strategy

import game.Step
import game.StrategyStep

class FullKindStrategy : AbstractStrategy() {

    override fun getName(): String {
        return "FullKindStrategy"
    }

    override fun move(steps: List<Step>): StrategyStep {
        return StrategyStep.COOPERATE
    }
}