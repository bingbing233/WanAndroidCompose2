package com.example.wanandroid.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.wanandroid.model.Article
import com.example.wanandroid.model.HomeResult

class ArticlePagingResource(private val getHomeResult:suspend (Int)->HomeResult) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key?:1
        val homeResult = getHomeResult(page)
        val preKey = if(page>1) page-1 else null
        val nextKey = page + 1
       return  LoadResult.Page(homeResult.data.articles,preKey,nextKey)
    }
}