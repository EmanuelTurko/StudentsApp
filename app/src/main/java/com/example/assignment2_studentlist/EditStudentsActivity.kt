package com.example.assignment2_studentlist

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment2_studentlist.Model.Model
import com.example.assignment2_studentlist.databinding.ActivityEditStudentsBinding

class EditStudentsActivity : AppCompatActivity() {
    var binding: ActivityEditStudentsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_students)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val studentIndex = intent.getIntExtra("studentIndex", -1)
        val student = Model.shared.students[studentIndex]

        binding = ActivityEditStudentsBinding.inflate(layoutInflater, findViewById(R.id.main), false)
        binding?.editName?.text = "name:"
        binding?.editId?.text = "ID:"
        binding?.editPhone?.text = "Phone:"
        binding?.editAddress?.text = "Address:"

        student.name.also { binding?.editNameEdit?.hint = it }
        student.id.also { binding?.editIdEdit?.hint = it }
        student.phone.also { binding?.editPhoneEdit?.hint = it }
        student.address.also { binding?.editAddressEdit?.hint = it }
        student.isChecked.also {binding?.editCheckbox?.isChecked = it}

        binding?.editSaveButton?.setOnClickListener{
            if(binding?.editNameEdit?.text.toString().isNotEmpty()) student.name = binding?.editNameEdit?.text.toString()
            if(binding?.editIdEdit?.text.toString().isNotEmpty()) student.id = binding?.editIdEdit?.text.toString()
            if(binding?.editPhoneEdit?.text.toString().isNotEmpty()) student.phone = binding?.editPhoneEdit?.text.toString()
            if(binding?.editAddressEdit?.text.toString().isNotEmpty()) student.address = binding?.editAddressEdit?.text.toString()
            student.isChecked = binding?.editCheckbox?.isChecked ?: false
            val intent = Intent(this, StudentListActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
        binding?.editDeleteButton?.setOnClickListener{
            Model.shared.students.removeAt(studentIndex)
            finish()
        }
        binding?.editCancelButton?.setOnClickListener{
            finish()
        }
        binding?.root?.let { setContentView(it) }
    }
}