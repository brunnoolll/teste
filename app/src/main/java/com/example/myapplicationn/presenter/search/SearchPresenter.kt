package com.example.myapplicationn.presenter.search

import com.example.myapplicationn.model.NewsResultado
import com.example.myapplicationn.model.data.NewsDataSource
import com.example.myapplicationn.presenter.ViewHome

class SearchPresenter (val view: ViewHome.View,
    private val dataSource: NewsDataSource): SearchHome.Presenter {
    override fun search(term: String) {
        this.view.showProgressBar()
        this.dataSource.searchNews(term, this)
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