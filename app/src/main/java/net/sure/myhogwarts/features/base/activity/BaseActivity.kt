package net.sure.myhogwarts.features.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.sure.myhogwarts.features.base.fragments.BaseDialogFragment
import net.sure.myhogwarts.features.extensions.initTransitions

abstract class BaseActivity : AppCompatActivity() {
    var activeDialogFragment: BaseDialogFragment? = null
    var isNewActivity: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTransitions()
        isNewActivity = true
        supportActionBar?.elevation = 0f
    }

}