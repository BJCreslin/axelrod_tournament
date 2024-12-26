package strategy

import game.Step
import game.StrategyStep
//Стратегия "Мошенник" выбирает предательство на протяжении игры, но может менять свое поведение в зависимости от того, насколько "выгодно" будет обманывать в будущем. Она может учитывать последние несколько ходов и выбирать момент для агрессивного поведения.
//
//Алгоритм:
//
//Ожидает момент, когда противник будет уязвим для предательства (например, если он сотрудничает слишком долго).
//Сильно агрессивно реагирует, когда противник становится "доверчивым".
class Cheater : AbstractStrategy() {

    private var cooperationCount = 0

    override fun getName(): String {
        return "Cheater"
    }

    override fun move(steps: List<Step>): StrategyStep {
        if (steps.isEmpty()) {
            return StrategyStep.BETRAYED
        }

        val lastStep = steps.last()
        if (lastStep.getOpponentMove(this) == StrategyStep.COOPERATE) {
            cooperationCount++
            if (cooperationCount > 2) {
                return StrategyStep.BETRAYED
            }
        } else {
            cooperationCount = 0
        }

        return StrategyStep.BETRAYED
    }
}
