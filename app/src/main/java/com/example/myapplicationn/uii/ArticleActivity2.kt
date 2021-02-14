package com.example.myapplicationn.uii


import android.webkit.WebViewClient
import com.example.myapplicationn.R
import com.example.myapplicationn.model.Article
import kotlinx.android.synthetic.main.activity_article2.*

class ArticleActivity2 : AbstractActivity() {

    private lateinit var articlee: Article

    override fun getLayout(): Int = R.layout.activity_article2


    override fun onInject() {


        webView.apply {
            webViewClient = WebViewClient()
            articlee.url?.let {url -> loadUrl(url)
            }
        }

        intent.extras?.let {  articleSend ->
            articlee =  articleSend.get("article") as Article
        }


    }
}