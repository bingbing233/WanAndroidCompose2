package com.example.wanandroid.repository

import com.example.wanandroid.model.HomeResult
import com.example.wanandroid.model.WxOfficialResult
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

    //首页数据
    @GET("article/list/{page}/json")
    suspend fun getArticle(@Path("page") page:Int=0):HomeResult

    //广场数据
    @GET("user_article/list/{page}/json")
    suspend fun getSquareArticle(@Path("page") page: Int=0):HomeResult

    //公众号
    @GET("wxarticle/chapters/json")
    suspend fun getWxOfficial():WxOfficialResult

    //公众号文章
    @GET("wxarticle/list/{id}/{page}/json")
    suspend fun getWxArticle(@Path("id")id:Int,@Path("page") page: Int=0):HomeResult
}