package net.sure.myhogwarts.features.spells.view

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.sure.myhogwarts.features.spells.SpellsRepository
import net.sure.myhogwarts.models.Spell

class ViewSpellViewModelFactory(private val spell: Spell, private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ViewSpellViewModel::class.java)){
            return ViewSpellViewModel(spell, application
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}