package net.sure.myhogwarts.features.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), ActivityTransitions {
    var activeDialogFragment: BaseDialogFragment? = null
    var isNewActivity: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTransitions(this)
        isNewActivity = true
        supportActionBar?.elevation = 0f
    }