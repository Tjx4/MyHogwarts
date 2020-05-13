package net.sure.myhogwarts.features.houses.all

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.sure.myhogwarts.features.houses.HousesRepository

class HousesViewModelFactory(private val housesRepository: HousesRepository, private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HousesViewModel::class.java)){
            return HousesViewModel(
                housesRepository,
                application
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}