package com.example.abrbarproject.model

data class UserLoggedInDto(
    val access: String,
    val refresh: String,
    val user_id: Int,
    val user_is: String,
    val userprofile_id: String
)