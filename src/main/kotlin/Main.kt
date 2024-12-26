package ru.bjcreslin

import game.Game
import kotlinx.coroutines.*
import strategy.*

fun main() {
    val strategies = listOf(
        FullKindStrategy(),
        FullEvilStrategy(),
        RandomStrategy(),
        TitForTat(),
        NiceStart(),
        GrimTrigger(),
        ForgivingStrategy(),
        TitForTwoTats(),
        DoubleBetrayal(),
        TitForBetrayal(),
        Cheater(),
    )
    val roundNumber = 100;
    val resultTable = HashMap<AbstractStrategy, Int>()

    fun addToMap(key: AbstractStrategy, value: Int) {
        resultTable[key] = resultTable.getOrPut(key) { 0 } + value
    }
    runBlocking {
        async {
            coroutineScope {
                val jobs = mutableListOf<Job>()
                for (i in strategies.indices) {
                    for (j in i + 1 until strategies.size) {
                        val strategy1 = strategies[i]
                        val strategy2 = strategies[j]
                        val game = Game(strategy1, strategy2, roundNumber)
                        val job = launch {
                            val matchResult = game.match()
                            addToMap(matchResult.firstStrategy, matchResult.firstPoint)
                            addToMap(matchResult.secondStrategy, matchResult.secondPoint)
                        }
                        jobs.add(job)
                    }
                }
                jobs.joinAll()
            }
        }
    }
    for ((key, value) in resultTable.entries.sortedByDescending { it.value }.associate { it.toPair() }) {
        println("${key.getName()}: $value")
    }

}