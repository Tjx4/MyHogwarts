package net.sure.myhogwarts.features.students

import net.sure.myhogwarts.features.base.repository.BaseRepository

class StudentsRepository : BaseRepository(){
    suspend fun getAllStudents(apiKey: String) = retrofitHelper?.allStudents(apiKey)
    suspend fun getStudent(apiKey: String, studentId: String) = retrofitHelper?.student(studentId, apiKey)
    suspend fun getStudentsInHouse(apiKey: String, house: String) = retrofitHelper?.studentsInHouse(apiKey, house)
    suspend fun getStudentsWithBloodStatus(apiKey: String, bloodStatus: String) = retrofitHelper?.studentsWithBloodStatus(apiKey, bloodStatus)
}