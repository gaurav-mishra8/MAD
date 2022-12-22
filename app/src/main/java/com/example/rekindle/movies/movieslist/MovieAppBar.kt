package com.example.rekindle.movies.movieslist

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rekindle.ui.theme.DarkColorPalette
import com.example.rekindle.ui.theme.LightColorPalette

@Composable
fun MovieAppBar(
    title: String,
    hintText: String,
    isExpanded: Boolean,
    onSearchOpen: () -> Unit,
    onSearchClose: () -> Unit,
    onSearchText: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val bgColor = if (isSystemInDarkTheme()) {
        LightColorPalette.secondary
    } else {
        DarkColorPalette.secondary
    }
    val query = rememberSaveable { mutableStateOf("") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(color = bgColor)
            .padding(6.dp)
    ) {
        if (isExpanded) {
            OutlinedTextField(
                value = query.value,
                onValueChange = { newText ->
                    query.value = newText
                    onSearchText(newText)
                },
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .background(color = Color.White)
                    .fillMaxWidth(),
                placeholder = {
                    Text(
                        text = hintText,
                        style = TextStyle(fontSize = 18.sp, color = Color.DarkGray)
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            query.value = ""
                            onSearchClose()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Close"
                        )
                    }
                }
            )

        } else {
            Text(
                text = title,
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = {
                    onSearchOpen()
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search"
                )
            }
        }
    }
}