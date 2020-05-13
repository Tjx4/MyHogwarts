package net.sure.myhogwarts.features.students.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_student.*
import net.sure.myhogwarts.R
import net.sure.myhogwarts.constants.STUDENT
import net.sure.myhogwarts.constants.PAYLOAD_KEY
import net.sure.myhogwarts.databinding.ActivityStudentBinding
import net.sure.myhogwarts.features.base.activity.BaseChildActivity
import net.sure.myhogwarts.models.Student

class ViewStudentActivity : BaseChildActivity() {
    private lateinit var binding: ActivityStudentBinding
    private lateinit var viewStudentViewModel: ViewStudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val student = intent.getBundleExtra(PAYLOAD_KEY)?.getParcelable<Student>(STUDENT)

        if(student == null) {
            finish()
            return
        }

        var application = requireNotNull(this).application
        var viewModelFactory = ViewStudentViewModelFactory(student, application)

        viewStudentViewModel = ViewModelProviders.of(this, viewModelFactory).get(ViewStudentViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_student)
        binding.viewStudentViewModel = viewStudentViewModel
        binding.lifecycleOwner = this

        supportActionBar?.title = student?.name
    }
}
