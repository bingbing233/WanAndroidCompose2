package com.example.wanandroid.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
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
                navController.navigate(router[it])
            }
        }) {
        val topPadding = it.calculateTopPadding()
        Column() {
            Spacer(modifier = Modifier
                .height(topPadding)
                .background(color = Color.Transparent))
                NavHost(navController = navController, startDestination = "home"){
                    composable("home"){
                        HomeList()
                    }
                    composable("square"){

                    }
                    composable("wx"){

                    }
                    composable("system"){

                    }
                    composable("project"){

                    }
                }
        }
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