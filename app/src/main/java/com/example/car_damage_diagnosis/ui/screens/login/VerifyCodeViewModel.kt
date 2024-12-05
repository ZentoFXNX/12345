package com.example.car_damage_diagnosis.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerifyCodeViewModel @Inject constructor() : ViewModel() {
    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber: StateFlow<String> = _phoneNumber.asStateFlow()

    private val _verificationCode = MutableStateFlow("")
    val verificationCode: StateFlow<String> = _verificationCode.asStateFlow()

    private val _isValidCode = MutableStateFlow(false)
    val isValidCode: StateFlow<Boolean> = _isValidCode.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _remainingResendTime = MutableStateFlow(0)
    val remainingResendTime: StateFlow<Int> = _remainingResendTime.asStateFlow()

    fun setPhoneNumber(number: String) {
        _phoneNumber.value = number
        startResendTimer()
    }

    fun updateVerificationCode(code: String) {
        // Limit to 6 digits
        val cleanCode = code.filter { it.isDigit() }.take(6)
        _verificationCode.value = cleanCode
        
        // Validate code length
        _isValidCode.value = cleanCode.length == 6
    }

    fun verifyCode(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            // TODO: Implement actual code verification logic
            delay(2000) // Simulate network call
            _isLoading.value = false
            onSuccess()
        }
    }

    fun resendCode() {
        viewModelScope.launch {
            _isLoading.value = true
            // TODO: Implement actual code resend logic
            delay(2000) // Simulate network call
            _isLoading.value = false
            startResendTimer()
        }
    }

    private fun startResendTimer() {
        viewModelScope.launch {
            _remainingResendTime.value = 60 // 60 seconds
            while (_remainingResendTime.value > 0) {
                delay(1000)
                _remainingResendTime.value--
            }
        }
    }
}
