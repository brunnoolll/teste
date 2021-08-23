package com.example.myapplicationn.presenter

import com.example.myapplicationn.model.Article

interface ViewHome {

    interface View{

        fun showProgressBar()
        fun showFailure(message: String)
        fun hideProgressBar()
        fun showArticles(articles: List<Article>)

    }

}