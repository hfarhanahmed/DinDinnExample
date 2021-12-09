package com.sevenpeakssoftware.farhan.ui.component.articles

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sevenpeakssoftware.farhan.R
import com.sevenpeakssoftware.farhan.data.Resource
import com.sevenpeakssoftware.farhan.data.dto.articles.Articles
import com.sevenpeakssoftware.farhan.databinding.HomeActivityBinding
import com.sevenpeakssoftware.farhan.ui.base.BaseActivity
import com.sevenpeakssoftware.farhan.ui.component.articles.adapter.ArticlesAdapter
import com.sevenpeakssoftware.farhan.utils.*
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by FarhanAhmed
 */
@AndroidEntryPoint
class ArticlesListActivity : BaseActivity() {
    private lateinit var binding: HomeActivityBinding

    private val articlesListViewModel: ArticlesListViewModel by viewModels()
    private lateinit var articlesAdapter: ArticlesAdapter

    override fun initViewBinding() {
        binding = HomeActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.article)
        val layoutManager = LinearLayoutManager(this)
        binding.rvRecipesList.layoutManager = layoutManager
        binding.rvRecipesList.setHasFixedSize(true)
        articlesListViewModel.getArticles()
    }


    private fun bindListData(articles: Articles) {
        if (!(articles.articlesList.isNullOrEmpty())) {
            articlesAdapter = ArticlesAdapter(articlesListViewModel, articles.articlesList)
            binding.rvRecipesList.adapter = articlesAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun observeSnackBarMessages(event: LiveData<SingleEvent<Any>>) {
        binding.root.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }

    private fun showDataView(show: Boolean) {
        binding.tvNoData.visibility = if (show) GONE else VISIBLE
        binding.rvRecipesList.visibility = if (show) VISIBLE else GONE
        binding.pbLoading.toGone()
    }

    private fun showLoadingView() {
        binding.pbLoading.toVisible()
        binding.tvNoData.toGone()
        binding.rvRecipesList.toGone()
    }

    private fun handleArticlesList(status: Resource<Articles>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> status.data?.let {
                bindListData(articles = it)
                articlesListViewModel.insertLocalArticles(it)
            }
            is Resource.LocalSuccess -> status.data?.let {
                bindListData(articles = it)
            }
            is Resource.DataError -> {
                articlesListViewModel.getLocalArticles()
            }
            is Resource.LocalDataError -> {
                showDataView(false)
                status.errorCode?.let { articlesListViewModel.showToastMessage(it) }
            }
        }
    }

    override fun observeViewModel() {
        observe(articlesListViewModel.articlesLiveData, ::handleArticlesList)
        observeSnackBarMessages(articlesListViewModel.showSnackBar)
        observeToast(articlesListViewModel.showToast)

    }
}
