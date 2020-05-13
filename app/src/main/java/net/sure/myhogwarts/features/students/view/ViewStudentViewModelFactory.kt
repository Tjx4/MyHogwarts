package net.sure.myhogwarts.features.students.view

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.sure.myhogwarts.models.Student

class ViewStudentViewModelFactory(private val student: Student, private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ViewStudentViewModel::class.java)){
            return ViewStudentViewModel(student, application) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}