package com.asiankoala.koawalib.control.feedforward

import com.asiankoala.koawalib.math.MathUtil.cos

class ArmFeedforward(
    private val kcos: Double,
    coefficients: FeedforwardCoefficients,
    kStatic: Double
) : MotorFeedforward(coefficients, kStatic) {
    override fun getFeedforward(x: Double, v: Double, a: Double): Double {
        return v * coefficients.kv + a * coefficients.ka + x.cos * kcos
    }
}
