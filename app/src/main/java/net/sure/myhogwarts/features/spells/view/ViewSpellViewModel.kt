package net.sure.myhogwarts.features.spells.view

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import net.sure.myhogwarts.features.spells.SpellsRepository
import kotlinx.coroutines.launch
import net.sure.myhogwarts.features.base.viewmodel.BaseVieModel
import net.sure.myhogwarts.models.Spell

class ViewSpellViewModel(private val spellsRepository: SpellsRepository, application: Application) : BaseVieModel(application)  {

    private var _isBusy: MutableLiveData<Boolean> = MutableLiveData()
    val isBusy: LiveData<Boolean>
        get() = _isBusy

    private var _spell: MutableLiveData<Spell?> = MutableLiveData()
    val spell: LiveData<Spell?>
        get() = _spell

    fun getSpellFromApi(spellId: String){
        _isBusy.value = true

        ioScope.launch {
            val spell = spellsRepository.getSpell(spellId, cacheHelper.apiKey)

            uiScope.launch {

                if(spell != null) {
                    _spell.value = spell
                }
                else{
                    // houses not found
                }

                _isBusy.value = false
            }
        }
    }

    fun setSpell(spell: Spell){
        _spell.value = spell
    }
}