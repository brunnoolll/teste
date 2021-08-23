package com.example.myapplicationn.model.data

import com.example.myapplicationn.network.RetrofitInstance
import com.example.myapplicationn.presenter.news.NewsHome
import com.example.myapplicationn.presenter.search.SearchHome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsDataSource {

    fun getInfoNews(callBack: NewsHome.Presenter){
        GlobalScope.launch(Dispatchers.Main){
          val response = RetrofitInstance.api.getInfoNews("br")
                if (response.isSuccessful){
                    response.body()?.let {  newsResponse ->
                        callBack.onSuccess(newsResponse)
                    }
                 callBack.onComplete()
                }else{
                    callBack.onError(response.message())
                    callBack.onComplete()
                }
        }
    }


    fun searchNews(terms: String, callback: SearchHome.Presenter ){
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstance.api.searchNews(terms)
                if(response.isSuccessful){
                    response.body()?.let { newsResponse ->
                        callback.onSuccess(newsResponse)
                    }
                callback.onComplete()
                }else{
                    callback.onError(response.message())
                    callback.onComplete()
                }

        }


    }


}