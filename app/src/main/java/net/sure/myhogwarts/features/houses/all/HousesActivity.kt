package net.sure.myhogwarts.features.houses.all

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import net.sure.myhogwarts.features.houses.view.ViewHouseActivity
import kotlinx.android.synthetic.main.activity_houses.*
import net.sure.myhogwarts.R
import net.sure.myhogwarts.adapters.HousesAdapter
import net.sure.myhogwarts.constants.HOUSE
import net.sure.myhogwarts.databinding.ActivityHousesBinding
import net.sure.myhogwarts.extensions.SLIDE_IN_ACTIVITY
import net.sure.myhogwarts.extensions.blinkView
import net.sure.myhogwarts.extensions.goToActivityWithPayload
import net.sure.myhogwarts.features.base.activity.BaseChildActivity
import net.sure.myhogwarts.features.houses.HousesRepository
import net.sure.myhogwarts.helpers.hideCurrentLoadingDialog
import net.sure.myhogwarts.helpers.showLoadingDialog
import net.sure.myhogwarts.models.House

class HousesActivity : BaseChildActivity(), HousesAdapter.HouseClickListener {
    private lateinit var binding: ActivityHousesBinding
    private lateinit var housesViewModel: HousesViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.houses)

        var housesRepository = HousesRepository()
        var application = requireNotNull(this).application
        var viewModelFactory = HousesViewModelFactory(housesRepository, application)

        housesViewModel = ViewModelProviders.of(this, viewModelFactory).get(HousesViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_houses)
        binding.housesViewModel = housesViewModel
        binding.lifecycleOwner = this

        housesViewModel.isBusy.observe(this, Observer { isBusy(it) })
        housesViewModel.isNoContent.observe(this, Observer { isNoContent(it) })
        housesViewModel.houses.observe(this, Observer { onHousesSet(it) })

        housesViewModel.getHousesFromApi()
    }

    private fun isBusy(isBusy: Boolean){
        if(isBusy)
            showLoadingDialog(getString(R.string.loading_houses), this)
        else
            hideCurrentLoadingDialog(this)
    }

    private fun isNoContent(isNoContent: Boolean){
        tvNoHouses.visibility = View.VISIBLE
    }

    private fun onHousesSet(houses: List<House?>?){
        rvHouses?.layoutManager = LinearLayoutManager(this)
        val housesAdapter = HousesAdapter(this, houses)
        housesAdapter.setClickListener(this)
        rvHouses?.adapter = housesAdapter
    }

    override fun onHouseClick(view: View, position: Int) {
        (view as FrameLayout).getChildAt(0).blinkView(0.5f, 1.0f, 400, 2, Animation.ABSOLUTE, 0, {
            val selectedHouse = housesViewModel.houses?.value?.get(position)

            var payload = Bundle()
            payload.putParcelable(HOUSE, selectedHouse)
            goToActivityWithPayload(ViewHouseActivity::class.java, payload, SLIDE_IN_ACTIVITY)
        }, {})
    }
}
