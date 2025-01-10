package com.example.assignment2_studentlist

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment2_studentlist.Model.Model
import com.example.assignment2_studentlist.Model.Student
import com.example.assignment2_studentlist.databinding.ActivityAddStudentBinding

class AddStudentActivity : AppCompatActivity() {

    var binding: ActivityAddStudentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityAddStudentBinding.inflate(layoutInflater, findViewById(R.id.main), false)
        "ID:".also { binding?.studentIdTextView?.text = it }
        "Name:".also { binding?.studentNameTextView?.text = it }
        "Phone:".also { binding?.studentPhoneTextView?.text = it }
        "Address:".also { binding?.studentAddressTextView?.text = it }
        binding?.studentIdEditText?.hint = "Enter ID"
        binding?.studentNameEditText?.hint = "Enter Name"
        binding?.studentPhoneEditText?.hint = "Enter Phone Number"
        binding?.studentAdressEditText?.hint = "Enter Address"
        binding?.addStudentCheckbox?.setOnClickListener(::onCheckboxClicked)
        binding?.addStudentResetButton?.setOnClickListener(::onResetClicked)
        binding?.addStudentSaveButton?.setOnClickListener(::onSaveClicked)
        binding?.root?.let { setContentView(it) }


    }
    fun onSaveClicked(view: View){
        val studentId = binding?.studentIdEditText?.text.toString()
        val studentName = binding?.studentNameEditText?.text.toString()
        val studentPhone = binding?.studentPhoneEditText?.text.toString()
        val studentAddress = binding?.studentAdressEditText?.text.toString()
        val student = Student(
            name = studentName,
            id = studentId,
            phone = studentPhone,
            address = studentAddress,
            avatarURL = "",
            isChecked = false)
        Model.shared.addStudent(student)
        finish()
    }
    fun onResetClicked(view: View){
        binding?.studentIdEditText?.setText("")
        binding?.studentNameEditText?.setText("")
        binding?.studentPhoneEditText?.setText("")
        binding?.studentAdressEditText?.setText("")
    }
    fun onCheckboxClicked(view: View){
        val isChecked = binding?.addStudentCheckbox?.isChecked
        binding?.addStudentCheckbox?.isChecked = isChecked != null && !isChecked
    }

}