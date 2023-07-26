package com.example.coursesapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.apiapp.LoginRequest
import com.example.apiapp.LoginResponse
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity: ComponentActivity() {
    lateinit var tv_email : TextInputEditText
    lateinit var tv_password : TextInputEditText
    lateinit var tv_not_account : TextView
    lateinit var btn_login : Button

    var key = ""
    var value = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        tv_email = findViewById(R.id.login_user)
        tv_password = findViewById(R.id.login_password)
        btn_login = findViewById(R.id.login_button)
        tv_not_account = findViewById(R.id.login_signup)

        tv_not_account.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener {
            log_in()
        }
    }

    fun log_in(){
        val apiService = ApiClient.getClient()

        val body : LoginRequest = LoginRequest(tv_email.text.toString(),tv_password.text.toString())

        val call = apiService.login(body)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if(response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse?.token != null){
                        key = "name"
                        value = loginResponse?.name!!
                        saveOnCache(applicationContext, key, value)
                        val intentList = Intent(applicationContext, CoursesListActivity::class.java)
                        startActivity(intentList)
                    }else{
                        Toast.makeText(applicationContext, "Credenciales incorrectas", Toast.LENGTH_LONG).show()
                    }
                }else{
                    Log.d("Error", "Hubo un error de login")
                    Toast.makeText(applicationContext, "Error OK", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Error de red", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun saveOnCache(context: Context, key: String, value: String){
        val sharedPreferences = context.getSharedPreferences("cache", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }
}