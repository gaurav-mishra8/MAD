package com.example.rekindle

import com.example.rekindle.photos.Image
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import javax.inject.Inject

class PopularImageRepo @Inject constructor() {

    private val mockImages = listOf<Image>(
        Image(
            id = "1",
            url = "https://images.pexels.com/photos/2014422/pexels-photo-2014422.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            title = "image1"
        ),
        Image(
            id = "2",
            url = "https://images.pexels.com/photos/3573351/pexels-photo-3573351.png?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            title = "image2"
        ),
        Image(
            id = "3",
            url = "https://images.pexels.com/photos/3573351/pexels-photo-3573351.png?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            title = "image3"
        )
    )

    suspend fun getPopularImages() = coroutineScope {
        delay(1000)
        mockImages
    }
}