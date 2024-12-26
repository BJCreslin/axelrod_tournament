package strategy

import game.Step
import game.StrategyStep

//1. Стратегия "Доброе начало" (Nice Start)
//Эта стратегия похожа на "Око за око", но она начинает с сотрудничества, а затем всегда продолжает сотрудничество, если противник сотрудничает, или же прощает одно предательство.
//
//Алгоритм:
//
//Начинать с сотрудничества.
//Если противник предал, мы прощаем его и снова сотрудничаем в следующем раунде.
//Если противник снова предал, мы можем также ответить предательством, но это будет исключением, и стратегия вернется к сотрудничеству после одного предательства.
class NiceStart : AbstractStrategy(){

    private var hasBeenBetrayed = false

    override fun getName(): String {
        return "Nice Start"
    }

    override fun move(steps: List<Step>): StrategyStep {
        if (steps.isEmpty()) {
            return StrategyStep.COOPERATE
        }

        val lastStep = steps.last()
        if (lastStep.getOpponentMove(this) == StrategyStep.BETRAYED) {
            if (!hasBeenBetrayed) {
                hasBeenBetrayed = true
                return StrategyStep.COOPERATE
            } else {
                return StrategyStep.BETRAYED
            }
        }

        return StrategyStep.COOPERATE
    }
}