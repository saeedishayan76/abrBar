package com.example.abrbarproject.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.abrbarproject.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel
@Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val phoneNumberLiveData = MutableLiveData<String>()

    fun authUser(phoneNumber: String) = authRepository.authUser(phoneNumber).asLiveData()

    fun validateCode( phoneNumber: String , code:String) = authRepository.validateCode(phoneNumber, code).asLiveData()

    fun setNumber(phoneNumber: String) = phoneNumber.also { this.phoneNumberLiveData.value = it }

    fun getNumber() = this.phoneNumberLiveData
}