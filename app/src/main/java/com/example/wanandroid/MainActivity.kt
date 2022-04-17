package com.example.wanandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wanandroid.ui.WebPage
import com.example.wanandroid.ui.home.HomePage
import com.example.wanandroid.ui.theme.WanAndroidTheme

class MainActivity : ComponentActivity() {

    val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WanAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val showWhichPage = viewModel.showWhichPage.collectAsState()
                    when(showWhichPage.value){
                        is ShowWhichPage.ShowHome -> {
                            HomePage()
                        }
                        else -> {

                        }
                    }
                }
            }
        }
    }
}
