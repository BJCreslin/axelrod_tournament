package strategy

import game.Step
import game.StrategyStep

//Эта стратегия похожа на "Око за око", но она более терпима. Она только отвечает предательством, если противник предал два раза подряд. Таким образом, она прощает одно предательство и только на второе отвечает.
//
//Алгоритм:
//
//Начинать с сотрудничества.
//Если противник предал один раз, простить и продолжить сотрудничество.
//Если противник предал дважды подряд, ответить предательством.

class TitForTwoTats : AbstractStrategy() {

    private var betrayalCount = 0

    override fun getName(): String {
        return "Tit for Two Tats"
    }

    override fun move(steps: List<Step>): StrategyStep {
        if (steps.isEmpty()) {
            return StrategyStep.COOPERATE
        }

        val lastStep = steps.last()
        if (lastStep.getOpponentMove(this) == StrategyStep.BETRAYED) {
            betrayalCount++
        } else {
            betrayalCount = 0
        }

        return if (betrayalCount >= 2) StrategyStep.BETRAYED else StrategyStep.COOPERATE
    }
}
