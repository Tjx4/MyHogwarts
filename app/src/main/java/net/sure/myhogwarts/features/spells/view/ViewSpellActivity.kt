package net.sure.myhogwarts.features.spells.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import net.sure.myhogwarts.features.spells.SpellsRepository
import co.za.dvt.myskilldevapp.features.spells.view.ViewSpellViewModelFactory
import net.sure.myhogwarts.R
import net.sure.myhogwarts.constants.PAYLOAD_KEY
import net.sure.myhogwarts.constants.SPELL
import net.sure.myhogwarts.databinding.ActivitySpellBinding
import net.sure.myhogwarts.features.base.activity.BaseChildActivity
import net.sure.myhogwarts.models.Spell

class ViewSpellActivity : BaseChildActivity() {
    private lateinit var binding: ActivitySpellBinding
    private lateinit var viewSpellViewModel: ViewSpellViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val spell = intent.getBundleExtra(PAYLOAD_KEY)?.getParcelable<Spell>(SPELL)

if(spell == null) {
    finish()
    return
}
        var spellsRepository = SpellsRepository()
        var application = requireNotNull(this).application
        var viewModelFactory = ViewSpellViewModelFactory(spellsRepository, application)

        viewSpellViewModel = ViewModelProviders.of(this, viewModelFactory).get(ViewSpellViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_spell)
        binding.viewSpellViewModel = viewSpellViewModel
        binding.lifecycleOwner = this

        // viewSpellViewModel.isBusy.observe(this, Observer { isBusy(it) })
        // viewSpellViewModel.spell.observe(this, Observer { onSpellSet(it) })

        supportActionBar?.title = spell?.spell

        viewSpellViewModel.setSpell(spell)

    }

    /*
    private fun isBusy(isBusy: Boolean){

    }

    private fun onSpellSet(spell: Spell?){
    }
    */
}
