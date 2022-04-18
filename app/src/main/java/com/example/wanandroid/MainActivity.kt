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
import com.example.wanandroid.ui.WebPage
import com.example.wanandroid.ui.home.HomePage
import com.example.wanandroid.ui.theme.WanAndroidTheme

class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WanAndroidTheme {
                val page = viewModel.curPage.collectAsState()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomePage()
                    AnimatedVisibility(visible = page.value is Page.Web) {
                        WebPage()
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        if(viewModel.curPage.value !is Page.Home){
            viewModel.setPage(Page.Home)
        }else{
            super.onBackPressed()
        }
    }
}
