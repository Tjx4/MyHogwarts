package net.sure.myhogwarts.extensions

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.sure.myhogwarts.R
import net.sure.myhogwarts.constants.ACTIVITY_TRANSITION
import net.sure.myhogwarts.constants.PAYLOAD_KEY
import net.sure.myhogwarts.models.ActivityTransition

fun Activity.initTransitions() {
    try {
        val activityTransition = this.intent.getBundleExtra(PAYLOAD_KEY).getIntArray(ACTIVITY_TRANSITION)
        this.overridePendingTransition(activityTransition!![0], activityTransition[1])
    }
    catch (e: Exception) {
        Log.e("AT", "$e")
    }
}

fun AppCompatActivity.goToActivityWithNoPayload(activity: Class<*>, activityTransitionAnimation: ActivityTransition) {
    goToActivity(activity, activityTransitionAnimation, null)
}

fun AppCompatActivity.goToActivityWithPayload(activity: Class<*>, payload: Bundle, activityTransitionAnimation: ActivityTransition) {
    goToActivity(activity, activityTransitionAnimation, payload)
}

private fun AppCompatActivity.goToActivity(activity: Class<*>, activityTransitionAnimation: ActivityTransition, payload: Bundle?) {
    val intent = Intent(this, activity)

    val fullPayload = payload ?: Bundle()
    fullPayload.putIntArray(ACTIVITY_TRANSITION, intArrayOf(activityTransitionAnimation.inAnimation, activityTransitionAnimation.outAnimation))

    intent.putExtra(PAYLOAD_KEY, fullPayload)
    startActivity(intent)
}

private fun getTransitionAnimation(inAnimation: Int, outAnimation: Int): ActivityTransition {
    val transitionProvider = ActivityTransition()
    transitionProvider.inAnimation = inAnimation
    transitionProvider.outAnimation = outAnimation
    return transitionProvider
}

val SLIDE_IN_ACTIVITY = getTransitionAnimation(R.anim.slide_right, R.anim.no_transition)
val SLIDE_OUT_ACTIVITY =  getTransitionAnimation(R.anim.no_transition, R.anim.slide_left)
val FADE_IN_ACTIVITY = getTransitionAnimation(R.anim.fade_in, R.anim.no_transition)
val FADE_OUT_ACTIVITY = getTransitionAnimation(R.anim.no_transition, R.anim.fade_out)