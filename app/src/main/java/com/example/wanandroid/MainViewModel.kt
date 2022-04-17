package com.example.wanandroid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wanandroid.model.Article
import com.example.wanandroid.repository.WanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {

    companion object{
         val icons = arrayListOf(
            R.drawable.ic_baseline_home_24,
            R.drawable.ic_baseline_beach_access_24,
            R.drawable.ic_baseline_android_24,
            R.drawable.ic_baseline_list_alt_24,
            R.drawable.ic_baseline_folder_24
        )

        val labels = arrayListOf("主页", "广场", "公众号", "体系", "项目")
    }

    //当前页面
    val curPage = MutableStateFlow(0)

    val showWhichPage = MutableStateFlow(ShowWhichPage.ShowHome)

    fun getHomeArticle(): Flow<PagingData<Article>> {
       return WanRepository.getHomeArticle().cachedIn(viewModelScope)
    }
}

sealed class ShowWhichPage{
    object ShowHome : ShowWhichPage()
    object ShowWeb : ShowWhichPage()
    object ShowOther : ShowWhichPage()
}