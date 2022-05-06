package com.example.wanandroid.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.wanandroid.MainViewModel
import com.example.wanandroid.Page
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun SquareList() {
    val viewModel: MainViewModel = viewModel()
    val article = viewModel.getSquareArticle().collectAsLazyPagingItems()
    val refreshState = rememberSwipeRefreshState(isRefreshing = false)
    SwipeRefresh(
        state = refreshState,
        onRefresh = { article.refresh()
        }) {
        refreshState.isRefreshing = article.loadState.refresh is LoadState.Loading
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "数据加载中...", style = TextStyle(color = androidx.compose.ui.graphics.Color.LightGray))
            LazyColumn(Modifier.padding(10.dp)) {
                items(article.itemCount){ index->
                    Column {
                        HomeListItem(article = article[index]!!, onClick = {
                            viewModel.selectArticle = it
                            viewModel.setPage(Page.Web)
                        })
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }
            }
        }
    }

}
