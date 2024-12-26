package strategy

import game.Step
import game.StrategyStep

//Начать с сотрудничества.
//Если противник предает хотя бы один раз, всегда предавать в последующих раундах.
class GrimTrigger : AbstractStrategy() {

    private var isBetrayedOnce = false

    override fun getName(): String {
        return "Grim Trigger"
    }

    override fun move(steps: List<Step>): StrategyStep {
        if (steps.isEmpty()) {
            return StrategyStep.COOPERATE
        }

        val lastStep = steps.last()
        if (lastStep.getOpponentMove(this) == StrategyStep.BETRAYED) {
            isBetrayedOnce = true
        }

        return if (isBetrayedOnce) StrategyStep.BETRAYED else StrategyStep.COOPERATE
    }
}