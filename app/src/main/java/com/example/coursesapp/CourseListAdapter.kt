package com.example.coursesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ProgressBar
import android.widget.TextView

class CourseListAdapter(private val context: Context, private val arrayList: java.util.ArrayList<Course>): BaseAdapter() {
    lateinit var name : TextView
    lateinit var percentage_text : TextView
    lateinit var percentage_progress_bar : ProgressBar

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)

        name = convertView.findViewById(R.id.item_course_name)
        percentage_text = convertView.findViewById(R.id.item_percentage)
        percentage_progress_bar = convertView.findViewById(R.id.item_progress_bar)

        name.text = arrayList[position].name
        percentage_text.text = "" + arrayList[position].completed_percentage + "%"
        percentage_progress_bar.progress = arrayList[position].completed_percentage

        return convertView
    }
}