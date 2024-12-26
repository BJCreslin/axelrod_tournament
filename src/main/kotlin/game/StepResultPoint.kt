package game

enum class StepResultPoint(val number: Int) {
    BOTH_COOPERATE(3),
    ONE_BETRAYED(5),
    BOTH_BETRAYED(1),
    ONE_COOPERATE(0)
}