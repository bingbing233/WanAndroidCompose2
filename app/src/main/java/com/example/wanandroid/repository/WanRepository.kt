package com.example.wanandroid.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.wanandroid.model.Article
import kotlinx.coroutines.flow.Flow

object WanRepository {

    private val api = WanApi.create()

    fun getHomeArticle(): Flow<PagingData<Article>> {
        return Pager(config = PagingConfig(15),) {
            ArticlePagingResource { api.getArticle(it) }
        }.flow
    }
}