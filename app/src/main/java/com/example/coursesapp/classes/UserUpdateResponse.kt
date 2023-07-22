package com.example.apiapp

import com.google.gson.annotations.SerializedName

data class UserUpdateResponse(
    @SerializedName("name") val name : String,
    @SerializedName("token") val token : String
)