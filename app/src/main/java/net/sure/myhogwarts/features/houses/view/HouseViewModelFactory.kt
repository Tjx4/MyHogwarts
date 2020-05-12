package net.sure.myhogwarts.features.houses.view

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.sure.myhogwarts.features.students.StudentsRepository

class ViewHouseModelFactory(private val studentsRepository: StudentsRepository, private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ViewHouseViewModel::class.java)){
            return ViewHouseViewModel(studentsRepository, application) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}