package com.example.myapplicationn.presenter.news

import com.example.myapplicationn.model.NewsResultado
import com.example.myapplicationn.model.data.NewsDataSource
import com.example.myapplicationn.presenter.ViewHome

class NewsPresenter (val view: ViewHome.View,
                     private val dataSource: NewsDataSource ):NewsHome.Presenter {
    override fun requestAll() {
        this.view.showProgressBar()
        this.dataSource.getInfoNews(this)
    }

    override fun onSuccess(newsResponse: NewsResultado) {
        this.view.showArticles(newsResponse.articles)
    }

    override fun onError(message: String) {
        this.view.showFailure(message)
    }

    override fun onComplete() {
        this.view.hideProgressBar()
    }


}