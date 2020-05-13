package net.sure.myhogwarts.features.houses.all

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import net.sure.myhogwarts.features.houses.HousesRepository
import kotlinx.coroutines.launch
import net.sure.myhogwarts.features.base.viewmodel.BaseVieModel
import net.sure.myhogwarts.models.House

class HousesViewModel(val housesRepository: HousesRepository, application: Application) : BaseVieModel(application)  {

    private var _isBusy: MutableLiveData<Boolean>  = MutableLiveData()
    val isBusy: LiveData<Boolean>
        get() = _isBusy

    private var _houses: MutableLiveData<List<House?>?> = MutableLiveData()
    val houses: LiveData<List<House?>?>
        get() = _houses

    fun getHousesFromApi(){
        _isBusy.value = true

        ioScope.launch {
            val houses = housesRepository.getHouses(cacheHelper.apiKey)

            uiScope.launch {

                if(!houses.isNullOrEmpty()) {
                    _houses.value = houses
                }
                else{
                    // houses not found
                }

                _isBusy.value = false
            }
        }
    }

}