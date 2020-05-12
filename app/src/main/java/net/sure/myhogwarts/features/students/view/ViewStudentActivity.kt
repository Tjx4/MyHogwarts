package net.sure.myhogwarts.features.students.view

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_student.*
import net.sure.myhogwarts.R
import net.sure.myhogwarts.constants.CHARACTER
import net.sure.myhogwarts.constants.PAYLOAD_KEY
import net.sure.myhogwarts.features.base.activity.BaseChildActivity
import net.sure.myhogwarts.models.Student

class ViewStudentActivity : BaseChildActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)

        val character = intent.getBundleExtra(PAYLOAD_KEY)?.getParcelable<Student>(CHARACTER)
        supportActionBar?.title = character?.name
        tvCharacterHouse.text = character?.house
        tvCharacterRole.text = character?.role
        tvSchool.text = character?.school
        tvSpecies.text = character?.species
        tvBloodStatus.text = character?.bloodStatus
    }
}
