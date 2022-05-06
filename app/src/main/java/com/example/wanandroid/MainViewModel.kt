package com.example.wanandroid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wanandroid.model.Article
import com.example.wanandroid.model.WxOfficial
import com.example.wanandroid.repository.WanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
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

    /**
     * HomePage当前的list页面
     */
    val curListPage = MutableStateFlow(0)
    val curPage = MutableStateFlow<Page>(Page.Home)
    var selectArticle:Article? = null //当前选中的文章，用于WebView展示
    val officialList = MutableStateFlow<List<WxOfficial>>(ArrayList())

    fun setCurListPage(page:Int){
        viewModelScope.launch {
            curListPage.emit(page)
        }
    }


    /**
     * 设置当前页面 Home/Web ...
     */
    fun setPage(page: Page){
        viewModelScope.launch {
            curPage.emit(page)
        }
    }


    //主页数据
    fun getHomeArticle(): Flow<PagingData<Article>> {
       return WanRepository.getHomeArticle().cachedIn(viewModelScope)
    }
    //广场数据
    fun getSquareArticle():Flow<PagingData<Article>>{
        return WanRepository.getSquareArticle().cachedIn(viewModelScope)
    }
    //公众号
    fun getWxOfficial(){
        viewModelScope.launch {
           officialList.emit(WanRepository.getWxOfficial().wxOfficials)
        }
    }
    //公众号文章
    fun getWxArticle(id:Int): Flow<PagingData<Article>> {
        return WanRepository.getWxArticle(id).cachedIn(viewModelScope)
    }
}

sealed class Page{
    object Home : Page()
    object Web : Page()
    object Other : Page()
}

//HomeList的状态
sealed class HomeListSate(){
    object Null:HomeListSate()
    object Loading:HomeListSate()
    object Error:HomeListSate()
}