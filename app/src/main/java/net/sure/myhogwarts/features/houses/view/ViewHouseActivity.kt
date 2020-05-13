package net.sure.myhogwarts.features.houses.view

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_house.*
import net.sure.myhogwarts.R
import net.sure.myhogwarts.adapters.CharactersAdapter
import net.sure.myhogwarts.constants.CHARACTER
import net.sure.myhogwarts.constants.HOUSE
import net.sure.myhogwarts.constants.PAYLOAD_KEY
import net.sure.myhogwarts.databinding.ActivityHouseBinding
import net.sure.myhogwarts.extensions.SLIDE_IN_ACTIVITY
import net.sure.myhogwarts.extensions.blinkView
import net.sure.myhogwarts.extensions.goToActivityWithPayload
import net.sure.myhogwarts.features.base.activity.BaseChildActivity
import net.sure.myhogwarts.features.students.StudentsRepository
import net.sure.myhogwarts.features.students.view.ViewStudentActivity
import net.sure.myhogwarts.models.House
import net.sure.myhogwarts.models.Student

class ViewHouseActivity : BaseChildActivity(), CharactersAdapter.CharacterClickListener {
    private lateinit var binding: ActivityHouseBinding
    private lateinit var viewHouseViewModel: ViewHouseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val house = intent.getBundleExtra(PAYLOAD_KEY)?.getParcelable<House>(HOUSE)

        if(house == null) {
            finish()
            return
        }

        var studentsRepository = StudentsRepository()
        var application = requireNotNull(this).application
        var viewModelFactory = ViewHouseModelFactory(studentsRepository, application)

        viewHouseViewModel = ViewModelProviders.of(this, viewModelFactory).get(ViewHouseViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_house)
        binding.viewHouseViewModel = viewHouseViewModel
        binding.lifecycleOwner = this

        viewHouseViewModel.house.observe(this, Observer { onHouseSet(it) })
        viewHouseViewModel.isBusy.observe(this, Observer { isBusy(it) })
        viewHouseViewModel.members.observe(this, Observer { onHouseMembersSet(it) })

        viewHouseViewModel.setHouse(house)
        viewHouseViewModel.getHouseMembersFromApi()
    }

    private fun isBusy(isBusy: Boolean){
        llMemberLoader.visibility = if(isBusy) View.VISIBLE else  View.GONE
    }

    private fun onHouseSet(house: House?){
        supportActionBar?.title = house?.name
        txtHouseMascot.text = house?.mascot
        txtHouseFounder.text = house?.founder
        txtHouseGhost.text = house?.houseGhost
        //txtHeadOfHouse.text = house?.headOfHouse
    }

    private fun onHouseMembersSet(members: List<Student?>?){
        rvMembers?.layoutManager = LinearLayoutManager(this)
        val charactersAdapter = CharactersAdapter(this, R.layout.small_character_layout, members)
        charactersAdapter.setClickListener(this)
        rvMembers?.adapter = charactersAdapter
    }

    override fun onHouseMemberClick(view: View, position: Int) {
        (view as FrameLayout).getChildAt(0).blinkView(0.5f, 1.0f, 400, 2, Animation.ABSOLUTE, 0, {
            val selectedCharacter = viewHouseViewModel.members?.value?.get(position)

            var payload = Bundle()
            payload.putParcelable(CHARACTER, selectedCharacter)
            goToActivityWithPayload(ViewStudentActivity::class.java, payload, SLIDE_IN_ACTIVITY)
        }, {})
    }
}
