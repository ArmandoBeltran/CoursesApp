package com.example.coursesapp

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apiapp.UserUpdateResponse
import com.example.coursesapp.classes.ChangePasswordRequest
import com.example.coursesapp.classes.ChangePasswordResponse
import com.example.coursesapp.classes.UserUpdateRequest
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangeDataActivity : AppCompatActivity() {
    lateinit var tiet_email    : TextInputEditText
    lateinit var tiet_user     : TextInputEditText
    lateinit var tiet_password : TextInputEditText
    lateinit var tiet_confirm_password : TextInputEditText
    lateinit var btn_change : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_change_data)

        tiet_email = findViewById(R.id.change_email)
        tiet_user  = findViewById(R.id.change_user)
        tiet_password = findViewById(R.id.change_password)
        tiet_confirm_password = findViewById(R.id.change_confirm_password)
        btn_change = findViewById(R.id.change_button)

        btn_change.setOnClickListener {
            changeUsername()
            changePassword()
        }
    }

    fun changeUsername(){
        val apiService = ApiClient.getClient()

        val token = readCache(this, "token")

        val body : UserUpdateRequest = UserUpdateRequest(token.toString(), tiet_email.text.toString(), tiet_user.text.toString())

        val call = apiService.userUpdate(body)
        call.enqueue(object : Callback<UserUpdateResponse> {
            override fun onResponse(
                call: Call<UserUpdateResponse>,
                response: Response<UserUpdateResponse>
            ) {
                if(response.isSuccessful) {
                    val userResponse = response.body()
                    Toast.makeText(applicationContext, "Proceso completado con éxito", Toast.LENGTH_LONG).show()
                    Toast.makeText(applicationContext, userResponse?.name!!, Toast.LENGTH_LONG).show()
                }else{
                    //Manejar el error de respuesta
                    Toast.makeText(applicationContext, "Error OK", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<UserUpdateResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Error de red", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun readCache(context: Context, key: String): String? {
        val sharedPreferences = context.getSharedPreferences("cache", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, null)
    }

    fun changePassword(){
        val apiService = ApiClient.getClient()

        val token = readCache(this, "token").toString()

        val body : ChangePasswordRequest = ChangePasswordRequest(token, tiet_password.text.toString())

        val call = apiService.passwordUpdate(body)
        call.enqueue(object : Callback<ChangePasswordResponse> {
            override fun onResponse(
                call: Call<ChangePasswordResponse>,
                response: Response<ChangePasswordResponse>
            ) {
                if(response.isSuccessful) {
                    val passwordResponse = response.body()
                    Toast.makeText(applicationContext, "Proceso completado con éxito", Toast.LENGTH_LONG).show()
                    Toast.makeText(applicationContext, passwordResponse?.password!!, Toast.LENGTH_LONG).show()
                }else{
                    //Manejar el error de respuesta
                    Toast.makeText(applicationContext, "Error OK", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ChangePasswordResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Error de red", Toast.LENGTH_LONG).show()
            }
        })
    }


}
