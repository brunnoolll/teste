package com.example.myapplicationn.presenter.search

import com.example.myapplicationn.model.NewsResultado

interface SearchHome {

    interface Presenter{
        fun search(term: String)
        fun onSuccess(newsResponse: NewsResultado)
        fun onError(message: String)
        fun onComplete()
    }

}