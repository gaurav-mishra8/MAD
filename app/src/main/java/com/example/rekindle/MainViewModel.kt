package com.example.rekindle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rekindle.photos.Image
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val popularImageRepo: PopularImageRepo
) : ViewModel() {

    val isLogin: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val popularImages = MutableStateFlow(emptyList<Image>())

    init {
        getImages()
    }

    fun onLoginClick() {
        isLogin.value = true
    }

    private fun getImages() {
        viewModelScope.launch {
            val images = popularImageRepo.getPopularImages()
            popularImages.value = images
        }
    }
}