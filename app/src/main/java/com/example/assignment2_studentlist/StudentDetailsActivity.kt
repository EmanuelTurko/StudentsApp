package com.example.assignment2_studentlist

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment2_studentlist.Model.Model
import com.example.assignment2_studentlist.databinding.ActivityStudentDetailsBinding

class StudentDetailsActivity : AppCompatActivity() {
    var binding : ActivityStudentDetailsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val studentIndex = intent.getIntExtra("studentIndex", -1)
        val student = Model.shared.students[studentIndex]
        binding = ActivityStudentDetailsBinding.inflate(layoutInflater, findViewById(R.id.main), false)
        student.name.also { binding?.editActivityName?.text = "Name: $it" }
        student.id.also { binding?.editAcivityId?.text = "ID: $it" }
        student.phone.also { binding?.editActivityPhone?.text = "Phone: $it" }
        student.address.also { binding?.editActivityAddress?.text = "Address: $it"}
        student.isChecked.also { binding?.editActivityCheckbox?.isChecked = it }
        binding?.editActivityCheckbox?.setOnClickListener {
            student.isChecked = binding?.editActivityCheckbox?.isChecked ?: false
        }
        binding?.editActivityButton?.setOnClickListener{
            intent.setClass(this, EditStudentsActivity::class.java)
            intent.putExtra("studentIndex", studentIndex)
            this.startActivity(intent)
        }
        binding?.DetailsCancelButton?.setOnClickListener{
            finish()
        }
        binding?.root?.let { setContentView(it) }
    }

}