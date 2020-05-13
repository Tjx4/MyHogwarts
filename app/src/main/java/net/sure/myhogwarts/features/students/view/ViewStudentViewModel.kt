package net.sure.myhogwarts.features.students.view

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import net.sure.myhogwarts.features.base.viewmodel.BaseVieModel
import net.sure.myhogwarts.models.Student

class ViewStudentViewModel(student: Student, application: Application) : BaseVieModel(application)  {

    private var _currentStudent: MutableLiveData<Student?> = MutableLiveData()
    val currentStudent: LiveData<Student?>
        get() = _currentStudent


    init {
        _currentStudent.value = student
    }

}