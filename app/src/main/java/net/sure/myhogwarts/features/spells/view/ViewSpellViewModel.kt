package net.sure.myhogwarts.features.spells.view

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import net.sure.myhogwarts.features.base.viewmodel.BaseVieModel
import net.sure.myhogwarts.models.Spell

class ViewSpellViewModel(spell: Spell, application: Application) : BaseVieModel(application)  {

    private var _currentSpell: MutableLiveData<Spell?> = MutableLiveData()
    val currentSpell: LiveData<Spell?>
        get() = _currentSpell

    init {
        _currentSpell.value = spell
    }

}