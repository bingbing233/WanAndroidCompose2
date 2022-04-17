package com.example.wanandroid.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wanandroid.R

@Preview
@Composable
fun Logo() {
    Card(shape = RoundedCornerShape(8.dp)) {
        Box(Modifier.background(color = Color.Magenta).padding(15.dp), contentAlignment = Alignment.Center) {
            Icon( painter = painterResource(id = R.drawable.ic_baseline_menu_book_24),"", tint = Color.White)
        }
    }
}