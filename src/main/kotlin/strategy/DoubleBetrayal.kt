package strategy

import game.Step
import game.StrategyStep

//Стратегия "Двойной обман" — это более агрессивная форма стратегии предательства. Она начинает с сотрудничества, но в случае, если противник предал, она отвечает сразу двумя предательствами подряд, чтобы максимизировать свою выгоду.
//
//Алгоритм:
//
//Начать с сотрудничества.
//Если противник предает, дважды предать в ответ.
class DoubleBetrayal : AbstractStrategy() {

    private var betrayalCount = 0

    override fun getName(): String {
        return "Double Betrayal"
    }

    override fun move(steps: List<Step>): StrategyStep {
        if (steps.isEmpty()) {
            return StrategyStep.COOPERATE
        }

        val lastStep = steps.last()
        if (lastStep.getOpponentMove(this) == StrategyStep.BETRAYED) {
            betrayalCount++
            if (betrayalCount < 2) {
                return StrategyStep.BETRAYED
            }
        }

        return StrategyStep.BETRAYED
    }
}
