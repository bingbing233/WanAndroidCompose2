package com.example.wanandroid.ui.home

import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.wanandroid.MainViewModel
import com.example.wanandroid.model.Article
import com.example.wanandroid.ui.theme.NameColor
import com.example.wanandroid.ui.theme.TagColor
import com.example.wanandroid.ui.theme.TimeColor
import com.example.wanandroid.ui.theme.TitleColor

@Composable
fun HomeList() {
    val viewModel: MainViewModel = viewModel()
    val article = viewModel.getHomeArticle().collectAsLazyPagingItems()
    LazyColumn(Modifier.padding(10.dp)) {
        repeat(article.itemCount) {
            item {
                Column {
                    HomeListItem(article = article[it]!!, onClick = {
                        //on item clicked
                    })
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    }
}

@Composable
fun HomeListItem(article: Article, onClick: (Article) -> Unit) {
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.clickable { onClick.invoke(article) }
    ) {
        Box(
            Modifier
                .padding(15.dp)) {
            Column(Modifier.fillMaxWidth()) {
                Text(
                    text = article.title,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = TitleColor
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Row() {
                        Text(text = article.author, color = NameColor, fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = article.chapterName, color = TagColor, fontSize = 14.sp)
                    }
                    Text(text = article.niceDate, color = TimeColor, fontSize = 14.sp)
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeListItemPrev() {
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(Modifier.padding(15.dp)) {
            Column(Modifier.fillMaxWidth()) {
                Text(
                    text = "Android获取设备唯一标识符完美解决方案title",
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = TitleColor
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Row() {
                        Text(text = "郭林", color = NameColor, fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "广场", color = TagColor, fontSize = 16.sp)
                    }
                    Text(text = "2020-11-12 10:00", color = TimeColor, fontSize = 14.sp)
                }
            }
        }
    }
}