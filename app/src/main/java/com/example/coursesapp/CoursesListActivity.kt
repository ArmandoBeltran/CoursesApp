package com.example.coursesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.coursesapp.classes.Session
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CoursesListActivity: ComponentActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var database : FirebaseDatabase

    lateinit var lv_courses_list : ListView

    var courses_array : ArrayList<Course> = ArrayList()
    var adapter : CourseListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_courses_list)

        lv_courses_list = findViewById(R.id.courses_list)

        auth = FirebaseAuth.getInstance()

        database = FirebaseDatabase.getInstance()

        getSessions()
    }

    override fun onStart(){
        super.onStart()

        val currentUser : FirebaseUser? = auth.currentUser
        if (currentUser != null){
            if (currentUser.email != ""){
                println("Chido")
            } else {
                println("No chido")
            }
        } else {
            login("beltran.armando2210@gmail.com", "123456789")
        }
    }

    fun login(email : String, password : String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                    task ->
                if (task.isSuccessful){
                    val user = auth.currentUser
                    //tv_txt.text = "Exitoso"
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_LONG).show()
                }else{
                    //tv_txt.text = "Error"
                    Toast.makeText(this, "Error en el inicio de sesión: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

    fun anoynmousLogin(){
        auth.signInAnonymously()
            .addOnCompleteListener(this) {
                    task ->
                if (task.isSuccessful){
                    val user = auth.currentUser
                    Toast.makeText(this, "INICIO anónimo", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Error en el inicio de sesión anónimo", Toast.LENGTH_LONG).show()
                }
            }
    }

    fun getSessions() {
        val reference = database.getReference("sessions")
        adapter = CourseListAdapter(applicationContext, courses_array)
        lv_courses_list.adapter = adapter

        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children) {
                    val session = data.getValue(Session::class.java)
                    Log.d("Name", session?.name.toString())
                    Log.d("Percentage", session?.percentage.toString())
                    courses_array.add(Course("test", 1))
                }

                adapter!!.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                println("Error al leer datos: ${error.message}")
            }
        })
    }
}