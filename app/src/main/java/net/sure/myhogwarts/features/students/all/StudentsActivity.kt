package net.sure.myhogwarts.features.students.all

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import net.sure.myhogwarts.features.students.StudentsRepository
import kotlinx.android.synthetic.main.activity_students.*
import net.sure.myhogwarts.features.students.view.ViewStudentActivity
import net.sure.myhogwarts.R
import net.sure.myhogwarts.adapters.CharactersAdapter
import net.sure.myhogwarts.constants.STUDENT
import net.sure.myhogwarts.databinding.ActivityStudentsBinding
import net.sure.myhogwarts.extensions.SLIDE_IN_ACTIVITY
import net.sure.myhogwarts.extensions.blinkView
import net.sure.myhogwarts.extensions.goToActivityWithPayload
import net.sure.myhogwarts.features.base.activity.BaseChildActivity
import net.sure.myhogwarts.helpers.hideCurrentLoadingDialog
import net.sure.myhogwarts.helpers.showLoadingDialog
import net.sure.myhogwarts.models.Student

class StudentsActivity : BaseChildActivity(), CharactersAdapter.CharacterClickListener {
    private lateinit var binding: ActivityStudentsBinding
    private lateinit var charactersViewModel: StudentsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.students)

        var studentsRepository =
            StudentsRepository()
        var application = requireNotNull(this).application
        var viewModelFactory = StudentsViewModelFactory(studentsRepository, application)

        charactersViewModel = ViewModelProviders.of(this, viewModelFactory).get(StudentsViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_students)
        binding.studentsViewModel = charactersViewModel
        binding.lifecycleOwner = this

        charactersViewModel.isBusy.observe(this, Observer { isBusy(it) })
        charactersViewModel.characters.observe(this, Observer { onCharactersSet(it) })

        charactersViewModel.getStudentsFromApi()
    }

    private fun isBusy(isBusy: Boolean){
        if(isBusy)
            showLoadingDialog(getString(R.string.loading_students), this)
        else
            hideCurrentLoadingDialog(this)
    }

    private fun onCharactersSet(students: List<Student?>?){
        if(students.isNullOrEmpty()) tvNoStudents.visibility = View.VISIBLE
        rvCharacters?.layoutManager = LinearLayoutManager(this)
        val housesAdapter = CharactersAdapter(this, R.layout.student_view ,students)
        housesAdapter.setClickListener(this)
        rvCharacters?.adapter = housesAdapter
    }

    override fun onHouseMemberClick(view: View, position: Int) {
        (view as FrameLayout).getChildAt(0).blinkView(0.5f, 1.0f, 400, 2, Animation.ABSOLUTE, 0, {
            val selectedCharacter = charactersViewModel.characters?.value?.get(position)

            var payload = Bundle()
            payload.putParcelable(STUDENT, selectedCharacter)
            goToActivityWithPayload(ViewStudentActivity::class.java, payload, SLIDE_IN_ACTIVITY)
        }, {})
    }

}
