package com.example.coursesapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class LoginActivity: ComponentActivity() {
    lateinit var tv_username : TextView
    lateinit var tv_password : TextView
    lateinit var tv_not_account : TextView
    lateinit var btn_login : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        //tv_username = findViewById(R.id.login_user)
        //tv_password = findViewById(R.id.login_password)
        btn_login = findViewById(R.id.login_button)
        tv_not_account = findViewById(R.id.login_signup)

        tv_not_account.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener {
            val intentList = Intent(this, CoursesListActivity::class.java)
            startActivity(intentList)
        }
    }
}