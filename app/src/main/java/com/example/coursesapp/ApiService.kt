package com.example.coursesapp

import com.example.apiapp.LoginRequest
import com.example.apiapp.LoginResponse
import com.example.apiapp.UserUpdateResponse
import com.example.coursesapp.classes.ChangePasswordRequest
import com.example.coursesapp.classes.ChangePasswordResponse
import com.example.coursesapp.classes.UserUpdateRequest
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Body

interface ApiService {

    @POST("login")
    fun login(@Body loginRequest: LoginRequest) : Call<LoginResponse>

    @POST("update_user")
    fun userUpdate(@Body userUpdateRequest: UserUpdateRequest) : Call<UserUpdateResponse>

    @POST("change_password")
    fun passwordUpdate(@Body changePasswordRequest: ChangePasswordRequest) : Call<ChangePasswordResponse>
}