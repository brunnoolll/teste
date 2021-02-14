package com.example.myapplicationn.uii

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationn.R
import com.example.myapplicationn.adapter.MainAdapter
import com.example.myapplicationn.model.Article
import com.example.myapplicationn.model.data.NewsDataSource
import com.example.myapplicationn.presenter.ViewHome
import com.example.myapplicationn.presenter.search.SearchPresenter
import com.example.myapplicationn.util.UtilQueryTextListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AbstractActivity(), ViewHome.View  {

    private val mainAdapterr by lazy{
        MainAdapter()
    }

    private lateinit var presenter: SearchPresenter

    override fun getLayout(): Int = R.layout.activity_search

    override fun onInject() {

        val dataSourcee = NewsDataSource()
        presenter = SearchPresenter(this, dataSourcee)
        configRecycle()
        searche()


        fun clickAdapter(){
            mainAdapterr.setOnclickListener {article ->
                val intent = Intent(this, ArticleActivity2::class.java)
                intent.putExtra("article",article)
                startActivity(intent)
            }
        }

    }

    private fun searche(){
            searchInfo.setOnQueryTextListener(UtilQueryTextListener(
                this.lifecycle
            ){
                newText -> newText?.let { query ->
                    if(query.isNotEmpty()){
                        presenter.search(query)
                        brProgressBarSearch.visibility = View.VISIBLE
                    }
                }
            }
        )
    }

    private fun configRecycle(){
        with(brSearch){
            adapter = mainAdapterr
            layoutManager = LinearLayoutManager(this@SearchActivity )
            addItemDecoration(DividerItemDecoration(this@SearchActivity, DividerItemDecoration.VERTICAL))
        }
    }




    override fun showProgressBar() {
        brProgressBarSearch.visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()
    }

    override fun hideProgressBar() {
        brProgressBarSearch.visibility = View.INVISIBLE
    }

    override fun showArticles(articles: List<Article>) {
        mainAdapterr.differ.submitList(articles.toList())
    }

}