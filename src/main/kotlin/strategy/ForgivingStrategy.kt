package strategy

import game.Step
import game.StrategyStep

class ForgivingStrategy : AbstractStrategy() {
    private var betrayals = 0

    override fun getName(): String {
        return "Forgiving Strategy"
    }

    override fun move(steps: List<Step>): StrategyStep {
        if (steps.isEmpty()) {
            return StrategyStep.COOPERATE
        }

        val lastStep = steps.last()
        if (lastStep.getOpponentMove(this) == StrategyStep.BETRAYED) {
            betrayals++
            if (betrayals > 1) {
                return StrategyStep.BETRAYED
            }
        } else {
            betrayals = 0
        }

        return StrategyStep.COOPERATE
    }
}