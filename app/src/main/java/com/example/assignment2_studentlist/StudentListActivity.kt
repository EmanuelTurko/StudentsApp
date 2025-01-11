package com.example.assignment2_studentlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.assignment2_studentlist.Model.Model
import com.example.assignment2_studentlist.Model.Student
import com.example.assignment2_studentlist.databinding.ActivityStudentListBinding
import org.w3c.dom.Text

class StudentListActivity : AppCompatActivity() {
    var students: MutableList<Student>? = null
    var binding: ActivityStudentListBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityStudentListBinding.inflate(layoutInflater, findViewById(R.id.main), false)
        val addButton = binding?.addStudentButton
        addButton?.setOnClickListener {
            intent.setClass(this, AddStudentActivity::class.java)
            this.startActivity(intent)
        }
        students = Model.shared.students
        val recyclerView = binding?.recyclerView
        recyclerView?.setHasFixedSize(true)
        val layoutManager =LinearLayoutManager(this)
        recyclerView?.layoutManager = layoutManager
        val adapter = StudentAdapter(students ?: emptyList())
        recyclerView?.adapter = adapter

        binding?.root?.let { setContentView(it) }

    }
    override fun onResume() {
        super.onResume()
        students = Model.shared.students
        val adapter = binding?.recyclerView?.adapter as? StudentAdapter
        adapter?.updateStudents(students ?: emptyList())
    }

    inner class StudentAdapter(private var students: List<Student>) :
        RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

        inner class StudentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val studentName : TextView = itemView.findViewById(R.id.studentRowName)
            val studentId : TextView = itemView.findViewById(R.id.studentRowId)
            val checkBox : CheckBox = itemView.findViewById(R.id.studentRowCheckbox)
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.student_list_row, parent, false)
            return StudentViewHolder(view)
        }

        override fun onBindViewHolder(holder: StudentViewHolder, pos:Int){
            val student = students[pos]
            holder.studentName.text = student.name
            holder.studentId.text = student.id
            holder.checkBox.isChecked = student.isChecked
            holder.checkBox.setOnClickListener { view ->
                val tag = view.tag as? Int
                val student = students[tag ?: return@setOnClickListener]
                student.isChecked = (view as? CheckBox)?.isChecked ?: false
            }

            holder.itemView.setOnClickListener {
                StuedntDetails(student,pos)
            }
        }

        override fun getItemCount(): Int = students.size

        fun updateStudents(newStudents: List<Student>){
            this.students = newStudents
            notifyDataSetChanged()
        }
    }
    private fun StuedntDetails(student: Student, pos:Int){
        intent.setClass(this, StudentDetailsActivity::class.java)
        intent.putExtra("studentIndex", pos)
        this.startActivity(intent)

    }
}
