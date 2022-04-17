package com.example.wanandroid.ui

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.wanandroid.R
import com.example.wanandroid.model.Article
import com.google.accompanist.insets.ui.Scaffold
import com.google.accompanist.insets.ui.TopAppBar


@Composable
fun WebPage(article: Article) {
    var progress by remember {
        mutableStateOf(0)
    }
    Scaffold(topBar = { WebTopBar() }) {
        WebView(url = article.link, onProcess = {
            progress = it
        })
    }
}

@Preview(showBackground = true)
@Composable
fun WebTopBar(title: String = "title",onClick:()->Unit ={},progress:Int = 0) {
    TopAppBar(title = {
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable { onClick() }, horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Text(text = title, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_down_24),
                    contentDescription = "down"
                )
            }
        }
        AnimatedVisibility(visible = progress in 1..99) {
            LinearProgressIndicator(progress = progress.toFloat())
        }
    })
}

@Composable
fun WebView(url: String, onProcess: (Int) -> Unit = {}) {
    AndroidView(factory = {
        val webView = android.webkit.WebView(it)
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
        }
        webView
    }, modifier = Modifier, update = {
        onProcess(it.progress)
    })
}