package net.sure.myhogwarts.features.students.view

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import net.sure.myhogwarts.features.students.StudentsRepository
import kotlinx.coroutines.launch
import net.sure.myhogwarts.features.base.viewmodel.BaseVieModel

class ViewStudentViewModel(val studentsRepository: StudentsRepository, application: Application) : BaseVieModel(application)  {

    private var _isBusy: MutableLiveData<Boolean> = MutableLiveData()
    val isBusy: LiveData<Boolean>
        get() = _isBusy

    fun getStudents(id: String){
        _isBusy.value = true

        ioScope.launch {
            val student = studentsRepository.getStudent(cacheHelper.apiKey, id)

            uiScope.launch {

                if(student != null) {
                    var dd = student
                }
                else{
                    // students not found
                }

                _isBusy.value = false
            }
        }
    }
}