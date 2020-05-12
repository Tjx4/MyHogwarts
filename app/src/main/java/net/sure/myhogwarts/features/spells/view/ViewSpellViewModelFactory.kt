package co.za.dvt.myskilldevapp.features.spells.view

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.sure.myhogwarts.features.spells.SpellsRepository
import net.sure.myhogwarts.features.spells.view.ViewSpellViewModel

class ViewSpellViewModelFactory(private val spellsRepository: SpellsRepository, private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ViewSpellViewModel::class.java)){
            return ViewSpellViewModel(
                spellsRepository,
                application
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}