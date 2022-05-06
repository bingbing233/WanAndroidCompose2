package com.example.wanandroid.ui.home.wx

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.wanandroid.MainViewModel
import com.example.wanandroid.Page
import com.example.wanandroid.ui.home.HomeListItem
import com.example.wanandroid.ui.theme.Purple500
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WxList() {
    val viewModel: MainViewModel = viewModel()
    val officialList = viewModel.officialList.collectAsState()
    val pageState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val refreshState = rememberSwipeRefreshState(isRefreshing = false)

    Column(Modifier.fillMaxSize()) {
        ScrollableTabRow(selectedTabIndex = pageState.currentPage, indicator = { tabPositions ->
            TabRowDefaults.Indicator(Modifier.pagerTabIndicatorOffset(pageState, tabPositions))
        }, modifier = Modifier.fillMaxWidth()) {
            officialList.value.forEachIndexed { index, wxOfficial ->
                Tab(selected = pageState.currentPage == index, onClick = {
                    scope.launch {
                        pageState.animateScrollToPage(index, 0f)
                    }
                }, text = { Text(text = officialList.value[index].name)})
            }
        }
    }
    HorizontalPager(count = officialList.value.size, state = pageState) { page->

        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Text(text = "hi hi hi" , fontSize = 20.sp, fontFamily = FontFamily.SansSerif)
        }
//        val article = viewModel.getWxArticle(officialList.value[page].id).collectAsLazyPagingItems()
//        SwipeRefresh(
//            state = refreshState,
//            onRefresh = { article.refresh()
//            }) {
//            refreshState.isRefreshing = article.loadState.refresh is LoadState.Loading
//            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                Text(text = "数据加载中...", style = TextStyle(color = Color.LightGray))
//                LazyColumn(Modifier.padding(10.dp)) {
//                    items(article.itemCount){ index->
//                        Column {
//                            HomeListItem(article = article[index]!!, onClick = {
//                                viewModel.selectArticle = it
//                                viewModel.setPage(Page.Web)
//                            })
//                            Spacer(modifier = Modifier.height(5.dp))
//                        }
//                    }
//                }
//            }
//        }
    }
}

