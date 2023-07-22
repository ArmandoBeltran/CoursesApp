package com.example.coursesapp.classes

import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(
    @SerializedName("token") val token       : String,
    @SerializedName("password") val password : String
)
