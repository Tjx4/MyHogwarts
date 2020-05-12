package net.sure.myhogwarts.helpers

import net.sure.myhogwarts.models.House
import net.sure.myhogwarts.models.Spell
import net.sure.myhogwarts.constants.*
import net.sure.myhogwarts.models.Student
import retrofit2.http.*

interface RetrofitHelper {
    @GET(GET_HOUSES)
    suspend fun houses(@Query("key") api_key: String?): List<House>?
    @GET(GET_HOUSE)
    suspend fun house(@Path(value = "houseId", encoded = true)studentId: String?, @Query("key") api_key: String?): House?

    @GET(GET_STUDENTS)
    suspend fun allStudents(@Query("key") api_key: String?): List<Student>?
    @GET(GET_STUDENT)
    suspend fun student(@Path(value = "studentId", encoded = true)studentId: String?, @Query("key") api_key: String?): Student?
    @GET(GET_STUDENTS)
    suspend fun studentsInHouse(@Query("key") api_key: String?, @Query("house") house: String?): List<Student>?
    @GET(GET_STUDENTS)
    suspend fun studentsWithBloodStatus(@Query("key") api_key: String?, @Query("bloodStatus") bloodStatus: String?): List<Student>?

    @GET(GET_SPELLS)
    suspend fun spells(@Query("key") api_key: String?): List<Spell>?
    @GET(GET_SPELL)
    suspend fun spell(@Path(value = "spellId", encoded = true)spellId: String?, @Query("key") api_key: String?): Spell?
}