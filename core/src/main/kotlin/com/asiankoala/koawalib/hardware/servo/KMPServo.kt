package com.asiankoala.koawalib.hardware.servo

import com.asiankoala.koawalib.control.profile.v2.Constraints
import com.asiankoala.koawalib.control.profile.v2.DispState
import com.asiankoala.koawalib.control.profile.v2.OnlineProfile
import com.asiankoala.koawalib.util.Periodic
import kotlin.math.absoluteValue

class KMPServo(
    private val servo: KServo,
    private val constraints: Constraints,
    private val threshold: Double
) : Periodic {
    private var profile: OnlineProfile? = null
    private var setpoint = servo.position
    private var target = servo.position
    val isAtTarget get() = (servo.position - target).absoluteValue < threshold

    fun setTarget(t: Double) {
        target = t
        profile = OnlineProfile(
            DispState(servo.position),
            DispState(target),
            constraints
        )
    }

    override fun periodic() {
        profile?.let {
            if(isAtTarget) {
                setpoint = it[setpoint].x
                servo.position = setpoint
            }
        }
    }
}