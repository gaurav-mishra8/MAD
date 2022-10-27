package com.example.rekindle

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.rekindle.ui.theme.RekindleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Cold Start", "MainActivity onCreate start")
        super.onCreate(savedInstanceState)

        val mainViewModel by viewModels<MainViewModel>()

        setContent {
            RekindleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val loginState = mainViewModel.isLogin.collectAsState(initial = false)
                    RenderHome(loginState.value) {
                        mainViewModel.onLoginClick()
                    }
                }
            }
        }
    }
}

@Composable
fun RenderHome(loginState: Boolean, onLoginClick: () -> Unit) {
    if (loginState) {
        Greeting(name = "Hello Gendry")
    } else {
        Login(onSubmit = {
            onLoginClick.invoke()
        })
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RekindleTheme {
        RenderHome(false) {}
    }
}