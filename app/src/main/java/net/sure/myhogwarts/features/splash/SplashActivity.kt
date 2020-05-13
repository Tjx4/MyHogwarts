package net.sure.myhogwarts.features.splash

import android.os.Bundle
import net.sure.myhogwarts.features.base.activity.BaseActivity
import net.sure.myhogwarts.features.dashboard.DashboardActivity
import net.sure.myhogwarts.extensions.FADE_IN_ACTIVITY
import net.sure.myhogwarts.extensions.goToActivityWithNoPayload

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goToActivityWithNoPayload(DashboardActivity::class.java, FADE_IN_ACTIVITY)
    }
}