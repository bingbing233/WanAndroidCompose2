package com.example.wanandroid.repository

import com.example.wanandroid.model.HomeResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface WanApi {

    companion object{
        const val baseUrl = "http://www.wanandroid.com"
        fun create():WanApi{
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WanApi::class.java)
        }
    }

    @GET("article/list/{page}/json")
    suspend fun getArticle(@Path("page") page:Int?=0):HomeResult

    @GET("user_article/list/{page}/json")
    suspend fun getSquareArticle(@Path("page") page: Int?=0):HomeResult

}