package com.example.myapplicationn.uii

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationn.R
import com.example.myapplicationn.adapter.MainAdapter
import com.example.myapplicationn.model.Article
import com.example.myapplicationn.model.data.NewsDataSource
import com.example.myapplicationn.presenter.ViewHome
import com.example.myapplicationn.presenter.news.NewsPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AbstractActivity() , ViewHome.View {

    private val mainAdapter by lazy{
        MainAdapter()
    }

    private lateinit var presenter: NewsPresenter

    override fun getLayout(): Int = R.layout.activity_main

    override fun onInject() {

        val dataSource = NewsDataSource()
        presenter = NewsPresenter(this, dataSource)
        presenter.requestAll()
        configRecycle()

        fun clickAdapter(){
            mainAdapter.setOnclickListener {article ->
                val intent = Intent(this, ArticleActivity2::class.java)
                intent.putExtra("article",article)
                startActivity(intent)
            }
        }

    }

    private fun configRecycle(){
        with(brNoticias){
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        }
    }




    override fun showProgressBar() {
        brProgressBar.visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()
    }

    override fun hideProgressBar() {
        brProgressBar.visibility = View.INVISIBLE
    }

    override fun showArticles(articles: List<Article>) {
        mainAdapter.differ.submitList(articles.toList())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.search_menu ->{
                Intent(this, SearchActivity::class.java).apply {
                    startActivity(this)
                }
            }
            R.id.favorito_menu ->{
                Intent(this, FavoritosActivity::class.java).apply {
                    startActivity(this)
                }

            }
        }

        return super.onOptionsItemSelected(item)
    }

}