package net.sure.myhogwarts.features.houses.view

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import net.sure.myhogwarts.features.base.viewmodel.BaseVieModel
import net.sure.myhogwarts.features.students.StudentsRepository
import net.sure.myhogwarts.models.House
import net.sure.myhogwarts.models.Student

class ViewHouseViewModel(private val studentsRepository: StudentsRepository, application: Application) : BaseVieModel(application)  {

    private var _house: MutableLiveData<House?> = MutableLiveData()
    val house: LiveData<House?>
        get() = _house

    private var _isBusy: MutableLiveData<Boolean> = MutableLiveData()
    val isBusy: LiveData<Boolean>
        get() = _isBusy

    private var _members: MutableLiveData<List<Student?>?> = MutableLiveData()
    val members: LiveData<List<Student?>?>
        get() = _members

    fun getHouseMembersFromApi(){
        _isBusy.value = true

        ioScope.launch {
            val members = studentsRepository.getStudentsInHouse(cacheHelper.apiKey, house.value?.name ?: "")

            uiScope.launch {

                if(!members.isNullOrEmpty()) {
                    _members.value = members
                }
                else{
                    // _members not found
                }

                _isBusy.value = false
            }
        }
    }

    fun setHouse(house: House){
        _house.value = house
    }
}