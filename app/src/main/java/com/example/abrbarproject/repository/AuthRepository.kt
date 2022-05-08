package com.example.abrbarproject.repository

import com.example.abrbarproject.model.AuthDto
import com.example.abrbarproject.model.UserLoggedInDto
import com.example.abrbarproject.repository.remote.ApiService
import com.example.abrbarproject.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepository
@Inject constructor(private val apiService: ApiService) {

    fun authUser(phoneNumber: String): Flow<Resource<AuthDto>> = flow {
        emit(Resource.Loading)
        try {

            val response = apiService.authUser(phoneNumber)
            emit(Resource.Success(data = response))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "error in fetch login"))
        }
    }

    fun validateCode(phoneNumber: String, code: String):Flow<Resource<UserLoggedInDto>> = flow {
        emit(Resource.Loading)
        try {

            val response = apiService.validateCode(phoneNumber , code)
            emit(Resource.Success(data = response))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "error in fetch validate "))
        }
    }
}