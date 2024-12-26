package strategy

import game.Step
import game.StrategyStep

class FullEvilStrategy : AbstractStrategy() {
    override fun getName(): String {
        return "Full Evil"
    }

    override fun move(steps: List<Step>): StrategyStep {
        return StrategyStep.BETRAYED
    }
}