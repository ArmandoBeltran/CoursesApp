package com.example.coursesapp.classes

import com.google.gson.annotations.SerializedName

data class ChangePasswordResponse(
    @SerializedName("token") val token       : String,
    @SerializedName("password") val password : String
)