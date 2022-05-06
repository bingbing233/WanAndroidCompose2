package com.example.wanandroid.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.wanandroid.model.Article
import com.example.wanandroid.model.WxOfficialResult
import kotlinx.coroutines.flow.Flow

object WanRepository {

    private val api = WanApi.create()

    fun getHomeArticle(): Flow<PagingData<Article>> {
        return Pager(config = PagingConfig(10),) {
            ArticlePagingResource(api::getArticle)
        }.flow
    }

    fun getSquareArticle():Flow<PagingData<Article>>{
        return Pager(config = PagingConfig(10)){
            SquarePagingResource(api::getSquareArticle)
        }.flow
    }

    suspend fun getWxOfficial(): WxOfficialResult {
        return api.getWxOfficial()
    }

    fun getWxArticle(id:Int):Flow<PagingData<Article>>{
        return Pager(config = PagingConfig(10)){
            WxArticlePagingResource(id,api::getWxArticle)
        }.flow
    }
}