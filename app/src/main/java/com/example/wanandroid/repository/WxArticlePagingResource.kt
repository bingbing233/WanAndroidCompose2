package com.example.wanandroid.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.wanandroid.model.Article
import com.example.wanandroid.model.HomeResult

class WxArticlePagingResource(val id:Int,private val getWxArticle:suspend (Int,Int)->HomeResult) : PagingSource<Int,Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        val result = kotlin.runCatching {
            val key = params.key?:1
            val preKey = if(key >= 1) key-1 else null
            val nextKey = key+1
            val articles = getWxArticle(id,key)
            LoadResult.Page(articles.data.articles,preKey,nextKey)
        }.onSuccess {
            return it
        }.onFailure {
            return LoadResult.Error(it)
        }
        return result.getOrThrow()
    }
}