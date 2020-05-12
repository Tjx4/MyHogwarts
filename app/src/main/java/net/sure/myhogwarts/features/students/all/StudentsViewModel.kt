package net.sure.myhogwarts.features.students.all

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import net.sure.myhogwarts.features.students.StudentsRepository
import kotlinx.coroutines.launch
import net.sure.myhogwarts.features.base.viewmodel.BaseVieModel
import net.sure.myhogwarts.models.Student

class StudentsViewModel(val studentsRepository: StudentsRepository, application: Application) : BaseVieModel(application)  {

    private var _isBusy: MutableLiveData<Boolean>  = MutableLiveData()
    val isBusy: LiveData<Boolean>
        get() = _isBusy

    private var _characters: MutableLiveData<List<Student?>?> = MutableLiveData()
    val characters: LiveData<List<Student?>?>
        get() = _characters

    fun getAndShowStudents(){
        _isBusy.value = true

        ioScope.launch {
            val students = studentsRepository.getAllStudents(cacheHelper.apiKey)

            uiScope.launch {

                if(!students.isNullOrEmpty()) {
                    _characters.value = students
                }
                else{
                    // students not found
                }

                _isBusy.value = false
            }
        }
    }



}