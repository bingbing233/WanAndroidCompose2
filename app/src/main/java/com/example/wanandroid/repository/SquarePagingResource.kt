package com.example.wanandroid.repository

import android.util.Log
import androidx.paging.PagingDataAdapter
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.wanandroid.model.Article
import com.example.wanandroid.model.HomeResult

class SquarePagingResource(private val getHomeResult: suspend (Int)->HomeResult) : PagingSource<Int,Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val result = kotlin.runCatching {
            val key = params.key?:1
            val preKey = if(key<0) key-1 else null
            val nextKey = key+1
            val homeResult = getHomeResult(key)
            LoadResult.Page(homeResult.data.articles,preKey,nextKey)
        }.onSuccess {
            Log.e("TAG", "load: ${it.data}", )
            return it
        }.onFailure {
            Log.e("TAG", "load: ", it)
            return LoadResult.Error(it)
        }
        return result.getOrThrow()
    }
}