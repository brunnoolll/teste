package com.example.myapplicationn.presenter.news

import com.example.myapplicationn.model.NewsResultado

interface NewsHome {

    interface Presenter{
        fun requestAll()
        fun onSuccess(newsResponse: NewsResultado)
        fun onError(message: String)
        fun onComplete()
    }
}