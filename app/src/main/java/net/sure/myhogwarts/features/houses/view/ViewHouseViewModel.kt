package net.sure.myhogwarts.features.houses.view

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import net.sure.myhogwarts.features.base.viewmodel.BaseVieModel
import net.sure.myhogwarts.features.students.StudentsRepository
import net.sure.myhogwarts.models.House
import net.sure.myhogwarts.models.Student

class ViewHouseViewModel(house: House, private val studentsRepository: StudentsRepository, application: Application) : BaseVieModel(application)  {

    private var _isBusy: MutableLiveData<Boolean> = MutableLiveData()
    val isBusy: LiveData<Boolean>
        get() = _isBusy

    private var _currentHouse: MutableLiveData<House?> = MutableLiveData()
    val currentHouse: LiveData<House?>
        get() = _currentHouse

    private var _members: MutableLiveData<List<Student?>?> = MutableLiveData()
    val members: LiveData<List<Student?>?>
        get() = _members

    init {
        _currentHouse.value = house
    }

    fun getHouseMembersFromApi(){
        _isBusy.value = true

        ioScope.launch {
            val members = studentsRepository.getStudentsInHouse(cacheHelper.apiKey, _currentHouse.value?.name ?: "")

            uiScope.launch {

                if(!members.isNullOrEmpty()) {
                    _members.value = members
                }

                _isBusy.value = false
            }
        }
    }
}