package strategy

import game.Step
import game.StrategyStep

class TitForTat : AbstractStrategy() {
    override fun getName(): String {
        return "TitForTat"
    }

    override fun move(steps: List<Step>): StrategyStep {
        // Если это первый ход, начинаем с сотрудничества
        if (steps.isEmpty()) {
            return StrategyStep.COOPERATE
        }
        // Если был хотя бы один шаг, копируем ход противника на предыдущем шаге
        val lastStep = steps.last()
        val lastOpponentMove = lastStep.getOpponentMove(this)
        return if (lastOpponentMove == StrategyStep.COOPERATE) StrategyStep.COOPERATE
        else StrategyStep.BETRAYED
    }
}