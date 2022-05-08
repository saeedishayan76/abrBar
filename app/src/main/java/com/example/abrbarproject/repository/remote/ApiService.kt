package com.example.abrbarproject.repository.remote

import com.example.abrbarproject.model.AuthDto
import com.example.abrbarproject.model.UserLoggedInDto
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("auth/dl-sms-codes/{PhoneNumber}/")
    suspend fun authUser(@Path("PhoneNumber") PhoneNumber: String):AuthDto

    @POST("auth/dl-sms-codes/{PhoneNumber}/codes/{code}/")
    suspend fun validateCode(@Path("PhoneNumber")PhoneNumber:String ,
    @Path("code")code:String):UserLoggedInDto
}