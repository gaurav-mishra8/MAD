package com.example.rekindle.photos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

data class Image(
    val id: String,
    val url: String,
    val title: String
)

@Composable
fun ImageList(images: List<Image>) {
    LazyColumn {
        items(images) { image ->
            ImageRow(image)
        }
    }
}

@Composable
fun ImageRow(image: Image) {
    Column {
        AsyncImage(
            model = image.url,
            contentDescription = image.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        Text(text = image.title)
    }
}