package net.sure.myhogwarts.features.spells.all

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import net.sure.myhogwarts.features.spells.SpellsRepository
import kotlinx.coroutines.launch
import net.sure.myhogwarts.features.base.viewmodel.BaseVieModel
import net.sure.myhogwarts.models.Spell

class SpellsViewModel(private val spellsRepository: SpellsRepository, application: Application) : BaseVieModel(application)  {

    private var _isBusy: MutableLiveData<Boolean> = MutableLiveData()
    val isBusy: LiveData<Boolean>
        get() = _isBusy

    private var _spells: MutableLiveData<List<Spell?>?> = MutableLiveData()
    val spells: LiveData<List<Spell?>?>
        get() = _spells

    fun getSpellsFromApi(){
        _isBusy.value = true

        ioScope.launch {
            val spells = spellsRepository.getAllSpells(cacheHelper.apiKey)

            uiScope.launch {

                if(!spells.isNullOrEmpty()) {
                    _spells.value = spells
                }
                else{
                    // houses not found
                }

                _isBusy.value = false
            }
        }
    }

}