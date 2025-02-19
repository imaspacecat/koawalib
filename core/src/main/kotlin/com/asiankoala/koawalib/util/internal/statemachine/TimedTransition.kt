package com.asiankoala.koawalib.util.internal.statemachine

internal class TimedTransition(val time: Double) : () -> Boolean {
    private var startTime = 0L

    fun startTimer() {
        startTime = System.nanoTime()
    }

    override fun invoke(): Boolean {
        return (System.nanoTime() - startTime) / 1e9 > time
    }
}
