package edu.liceo.fieldkit.hardware

import android.content.Context
import android.hardware.SensorManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import kotlin.math.abs
import kotlin.math.sqrt

fun isShake(v: FloatArray, threshold: Float = 12f): Boolean {
    val total = sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2])
    return abs(total - SensorManager.GRAVITY_EARTH) > threshold
}

fun Context.buzz(ms: Long = 80) {
    val vib: Vibrator = if (Build.VERSION.SDK_INT >= 31) {
        getSystemService(VibratorManager::class.java).defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }
    vib.vibrate(VibrationEffect.createOneShot(ms, VibrationEffect.DEFAULT_AMPLITUDE))
}
