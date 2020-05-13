package net.sure.myhogwarts.features.spells.all

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import net.sure.myhogwarts.features.spells.SpellsRepository
import net.sure.myhogwarts.features.spells.view.ViewSpellActivity
import kotlinx.android.synthetic.main.activity_spells.*
import kotlinx.android.synthetic.main.activity_students.*
import net.sure.myhogwarts.R
import net.sure.myhogwarts.adapters.SpellsAdapter
import net.sure.myhogwarts.constants.SPELL
import net.sure.myhogwarts.databinding.ActivitySpellsBinding
import net.sure.myhogwarts.extensions.SLIDE_IN_ACTIVITY
import net.sure.myhogwarts.extensions.blinkView
import net.sure.myhogwarts.extensions.goToActivityWithPayload
import net.sure.myhogwarts.features.base.activity.BaseChildActivity
import net.sure.myhogwarts.helpers.hideCurrentLoadingDialog
import net.sure.myhogwarts.helpers.showLoadingDialog
import net.sure.myhogwarts.models.Spell

class SpellsActivity : BaseChildActivity(), SpellsAdapter.SpellClickListener {

    private lateinit var binding: ActivitySpellsBinding
    private lateinit var spellsViewModel: SpellsViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var spellsRepository = SpellsRepository()
        var application = requireNotNull(this).application
        var viewModelFactory = SpellsViewModelFactory(spellsRepository, application)

        spellsViewModel = ViewModelProviders.of(this, viewModelFactory).get(SpellsViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_spells)
        binding.spellsViewModel = spellsViewModel
        binding.lifecycleOwner = this

        spellsViewModel.isBusy.observe(this, Observer { isBusy(it) })
        spellsViewModel.isNoContent.observe(this, Observer { isNoContent(it) })
        spellsViewModel.spells.observe(this, Observer { onSpellsSet(it) })

        supportActionBar?.title = getString(R.string.spells)

        spellsViewModel.getSpellsFromApi()
    }

    private fun isBusy(isBusy: Boolean){
        if(isBusy)
            showLoadingDialog(getString(R.string.loading_spells), this)
        else
            hideCurrentLoadingDialog(this)
    }

    private fun isNoContent(isNoContent: Boolean){
        tvNoSpells.visibility = View.VISIBLE
    }

    private fun onSpellsSet(spells: List<Spell?>?){
        rvSpells?.layoutManager = LinearLayoutManager(this)
        val spellsAdapter = SpellsAdapter(this, spells)
        spellsAdapter.setClickListener(this)
        rvSpells?.adapter = spellsAdapter
    }

    override fun onSpellClick(view: View, position: Int) {
        (view as FrameLayout).getChildAt(0).blinkView(0.5f, 1.0f, 400, 2, Animation.ABSOLUTE, 0, {
            val selectedSpell = spellsViewModel.spells?.value?.get(position)

            var payload = Bundle()
            payload.putParcelable(SPELL, selectedSpell)
            goToActivityWithPayload(ViewSpellActivity::class.java, payload, SLIDE_IN_ACTIVITY)
        }, {})
    }

}
