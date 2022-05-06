package com.example.wanandroid.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wanandroid.MainViewModel
import com.example.wanandroid.MainViewModel.Companion.icons
import com.example.wanandroid.MainViewModel.Companion.labels
import com.example.wanandroid.Page
import com.google.accompanist.insets.ui.Scaffold
import com.google.accompanist.insets.ui.TopAppBar
import kotlinx.coroutines.launch

@Composable
fun HomePage() {
    val viewModel: MainViewModel = viewModel()
    val page = viewModel.curListPage.collectAsState()
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val router = arrayListOf("home","square","wx","system","project")
    Scaffold(topBar = { HomeTopBar(tittle = labels[page.value]) {} },
        bottomBar = {
            HomeBottomBar(selectItem = page.value) {
                scope.launch {
                    viewModel.curListPage.emit(it)
                }
//                navController.navigate(router[it])
            }
        }) {
        val topPadding = it.calculateTopPadding()
        Column (
            Modifier
                .fillMaxSize()
                .padding(top = topPadding)
                .background(Color.Transparent)){
                when(page.value){
                    0 -> {
                        HomeList()
                    }
                    1 -> {
                        SquareList()
                    }
                    else -> {

                    }
                }
//                NavHost(navController = navController, startDestination = "home"){
//                    composable("home"){
//                        HomeList()
//                    }
//                    composable("square"){
//                        TempPage(title = "square")
//                    }
//                    composable("wx"){
//                        TempPage(title = "wx")
//
//                    }
//                    composable("system"){
//                        TempPage(title = "system")
//
//                    }
//                    composable("project"){
//                        TempPage(title = "project")
//                    }
//                }
        }
    }
}

@Composable
fun TempPage(title:String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = title)
    }
}

@Preview
@Composable
fun HomeTopBar(tittle: String = "title", onNavClick: () -> Unit = {}) {
    TopAppBar(title = { Text(text = tittle) }, navigationIcon = {
        IconButton(onClick = { onNavClick?.invoke() }) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "drawer")
        }
    })
}

@Preview
@Composable
fun HomeBottomBar(selectItem: Int = 0, onBottomItemClick: (Int) -> Unit = {}) {
    BottomAppBar() {
        repeat(icons.size) {
            BottomNavigationItem(
                selected = it == selectItem,
                onClick = { onBottomItemClick.invoke(it) },
                label = { Text(text = labels[it]) },
                icon = {
                        Icon(
                            painter = painterResource(id = icons[it]),
                            contentDescription = labels[it]
                        )
                })
        }
    }
}