package com.example.wanandroid.model

import com.google.gson.annotations.SerializedName

data class WxOfficialResult(
    @SerializedName("data")
    val wxOfficials: List<WxOfficial>,
    val errorCode: Int,
    val errorMsg: String
)

data class WxOfficial(
    val author: String,
    val children: List<Any>,
    val courseId: Int,
    val cover: String,
    val desc: String,
    val id: Int,
    val lisense: String,
    val lisenseLink: String,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)