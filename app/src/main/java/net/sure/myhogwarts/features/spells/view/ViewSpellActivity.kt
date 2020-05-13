package net.sure.myhogwarts.features.spells.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
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

        var application = requireNotNull(this).application
        var viewModelFactory = ViewSpellViewModelFactory(spell, application)

        viewSpellViewModel = ViewModelProviders.of(this, viewModelFactory).get(ViewSpellViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_spell)
        binding.viewSpellViewModel = viewSpellViewModel
        binding.lifecycleOwner = this

        supportActionBar?.title = spell?.spell
    }

}
