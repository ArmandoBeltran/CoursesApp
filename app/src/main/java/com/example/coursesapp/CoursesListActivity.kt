package com.example.coursesapp

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.activity.ComponentActivity

class CoursesListActivity: ComponentActivity() {

    lateinit var lv_courses_list : ListView

    var courses_array : ArrayList<Course> = ArrayList()
    var adapter : CourseListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_courses_list)

        lv_courses_list = findViewById(R.id.courses_list)

        courses_array.add(Course("Curso de Android", 60))
        courses_array.add(Course("Curso de C++", 10))
        adapter = CourseListAdapter(this, courses_array)
        lv_courses_list.adapter = adapter
    }
}