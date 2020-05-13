package net.sure.myhogwarts.features.base.repository

import net.sure.myhogwarts.constants.HOST
import net.sure.myhogwarts.helpers.RetrofitHelper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseRepository {

    protected var retrofitHelper: RetrofitHelper

    init {
        val builder = Retrofit.Builder()
            .baseUrl(HOST)
            .addConverterFactory(GsonConverterFactory.create())
        val retrofit = builder.build()

        retrofitHelper = retrofit.create(RetrofitHelper::class.java)
    }
}