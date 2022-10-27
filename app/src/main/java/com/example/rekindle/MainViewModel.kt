package com.example.rekindle

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel : ViewModel() {
    val isLogin: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun onLoginClick() {
        isLogin.value = true
    }

}