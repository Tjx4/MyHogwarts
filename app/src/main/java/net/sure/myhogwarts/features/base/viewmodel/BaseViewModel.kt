package net.sure.myhogwarts.features.base.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import net.sure.myhogwarts.helpers.CacheHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseVieModel(application: Application) : AndroidViewModel(application){
    protected var app = application
    protected var cacheHelper = CacheHelper(application.applicationContext)
    protected var viewModelJob = Job()
    protected val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)
    protected val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        cacheHelper.apiKey = "$2a$10$1JEnmtEF417yBaFZcr51qukRjaKv8d5toEG5DKP/IUZWIVwfsaF7y"
    }
}