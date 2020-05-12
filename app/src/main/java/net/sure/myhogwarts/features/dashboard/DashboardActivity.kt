package net.sure.myhogwarts.features.dashboard

import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_dashboard.*
import net.sure.myhogwarts.R
import net.sure.myhogwarts.adapters.FeaturesAdapter
import net.sure.myhogwarts.databinding.ActivityDashboardBinding
import net.sure.myhogwarts.enums.AppFeatures
import net.sure.myhogwarts.extensions.FADE_IN_ACTIVITY
import net.sure.myhogwarts.features.base.activity.BaseActivity
import net.sure.myhogwarts.extensions.blinkView
import net.sure.myhogwarts.extensions.goToActivityWithNoPayload
import net.sure.myhogwarts.features.houses.all.HousesActivity
import net.sure.myhogwarts.features.spells.all.SpellsActivity
import net.sure.myhogwarts.features.students.all.StudentsActivity

class DashboardActivity : BaseActivity(),  FeaturesAdapter.FeatureClickListener{
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setIcon(R.mipmap.ic_launcher_round)

        var application = requireNotNull(this).application
        var viewModelFactory = DashboardViewModelFactory(application)

        dashboardViewModel = ViewModelProviders.of(this, viewModelFactory).get(DashboardViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        binding.dashboardViewModel = dashboardViewModel
        binding.lifecycleOwner = this

        var features = AppFeatures.values()
        rvFeatures?.layoutManager = GridLayoutManager(this, 2)
        val featuresAdapter =  FeaturesAdapter(this, features)
        featuresAdapter.setClickListener(this)
        rvFeatures?.adapter = featuresAdapter
    }

    override fun onFeatureClick(view: View, position: Int) {
        view.blinkView(0.5f, 1.0f, 400, 2, Animation.ABSOLUTE, 0, {
            when(AppFeatures.values()[position]){
                AppFeatures.Students -> goToActivityWithNoPayload(StudentsActivity::class.java, FADE_IN_ACTIVITY)
                AppFeatures.Houses -> goToActivityWithNoPayload(HousesActivity::class.java, FADE_IN_ACTIVITY)
                AppFeatures.Spells -> goToActivityWithNoPayload(SpellsActivity::class.java, FADE_IN_ACTIVITY)
            }
        }, {})
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.dashboard_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true)
            return super.onKeyDown(keyCode, event)
        }

        return true
    }

}