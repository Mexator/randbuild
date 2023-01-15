package ru.mexator.randbuild.random

import kotlin.random.Random

fun <T> weightedRandom(list: List<T>, weight: (T) -> Int): T {
    if (list.size == 1) return list.first()
    val weights = list.map { weight(it) }

    // Cumulative Distribution Function
    val prefixWeights = weights.scan(0, Int::plus).drop(1)
    val sum = prefixWeights.last()
    val random = Random.nextInt(0, sum - 1)
    val result = prefixWeights.indexOfFirst { it >= random }
    return if (result == -1) list.last() else list[result]
}