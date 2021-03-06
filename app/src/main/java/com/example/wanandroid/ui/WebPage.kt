package com.example.wanandroid.ui

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wanandroid.MainViewModel
import com.example.wanandroid.Page
import com.example.wanandroid.R
import com.example.wanandroid.ui.theme.ProcessBarColor
import com.google.accompanist.insets.ui.Scaffold
import com.google.accompanist.insets.ui.TopAppBar
import kotlinx.coroutines.launch


@Composable
fun WebPage() {
    val viewModel: MainViewModel = viewModel()
    val article = viewModel.selectArticle
    val scope = rememberCoroutineScope()
    var progress by remember {
        mutableStateOf(0f)
    }
    Scaffold(topBar = {
        WebTopBar(title = article?.title ?: "", onClick = {
            scope.launch {
                viewModel.setPage(Page.Home)
            }
        })
    }) {
        val topPadding = it.calculateTopPadding()
        Column {
            Spacer(modifier = Modifier.height(topPadding))
            AnimatedVisibility(visible = progress<100) {
                LinearProgressIndicator(progress = progress/100, color = ProcessBarColor, modifier = Modifier.fillMaxWidth())
            }
            WebView(url = article?.link ?: "", onProcess = { p->
                progress = p
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WebTopBar(title: String = "title", onClick: () -> Unit = {}) {
    TopAppBar(title = {
        Column {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = title, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    },Modifier.clickable {onClick()  })
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebView(url: String, onProcess: (Float) -> Unit = {}) {
    AndroidView(factory = {
        val webView = WebView(it)
        webView.apply {
            settings.javaScriptEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.domStorageEnabled = true
            settings.loadsImagesAutomatically = true
            settings.mediaPlaybackRequiresUserGesture = true
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    return if (url.startsWith("http"))
                        true
                    else
                        super.shouldOverrideUrlLoading(view, request)
                }
            }

            Log.e("TAG", "WebView: $progress", )
        }
        webView.loadUrl(url)
        webView
    }, modifier = Modifier, update = {
       it.webChromeClient = object :WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                onProcess(newProgress.toFloat())
                super.onProgressChanged(view, newProgress)
            }
        }
    })
}