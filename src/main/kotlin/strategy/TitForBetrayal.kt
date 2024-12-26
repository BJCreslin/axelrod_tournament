package strategy

import game.Step
import game.StrategyStep

class TitForBetrayal : AbstractStrategy() {

    private var hasBeenBetrayed = false

    override fun getName(): String {
        return "Tit for Betrayal"
    }

    override fun move(steps: List<Step>): StrategyStep {
        if (steps.isEmpty()) {
            return StrategyStep.COOPERATE
        }

        val lastStep = steps.last()
        if (lastStep.getOpponentMove(this) == StrategyStep.BETRAYED) {
            hasBeenBetrayed = true
        }

        return if (hasBeenBetrayed) StrategyStep.BETRAYED else StrategyStep.COOPERATE
    }
}
