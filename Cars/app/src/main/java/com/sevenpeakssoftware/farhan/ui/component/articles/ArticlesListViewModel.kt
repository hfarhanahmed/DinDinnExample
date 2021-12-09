package com.sevenpeakssoftware.farhan.ui.component.articles

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sevenpeakssoftware.farhan.data.DataRepositorySource
import com.sevenpeakssoftware.farhan.data.Resource
import com.sevenpeakssoftware.farhan.data.dto.articles.Article
import com.sevenpeakssoftware.farhan.data.dto.articles.Articles
import com.sevenpeakssoftware.farhan.data.dto.articles.ArticlesItem
import com.sevenpeakssoftware.farhan.ui.base.BaseViewModel
import com.sevenpeakssoftware.farhan.utils.SingleEvent
import com.sevenpeakssoftware.farhan.utils.wrapEspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by FarhanAhmed
 */
@HiltViewModel
class ArticlesListViewModel @Inject
constructor(private val dataRepositoryRepository: DataRepositorySource) : BaseViewModel() {

    /**
     * Data --> LiveData, Exposed as LiveData, Locally in viewModel as MutableLiveData
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val recipesLiveDataPrivate = MutableLiveData<Resource<Articles>>()
    val articlesLiveData: LiveData<Resource<Articles>> get() = recipesLiveDataPrivate

    /**
     * Error handling as UI
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
    val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate


    fun getArticles() {
        viewModelScope.launch {
            recipesLiveDataPrivate.value = Resource.Loading()
            wrapEspressoIdlingResource {
                dataRepositoryRepository.requestArticles().collect {
                    recipesLiveDataPrivate.value = it
                }
            }
        }
    }

    fun getLocalArticles() {
        viewModelScope.launch {
            recipesLiveDataPrivate.value = Resource.Loading()
            wrapEspressoIdlingResource {
                dataRepositoryRepository.requestLocalArticles().collect {
                    recipesLiveDataPrivate.value = when (it.data) {
                        is List<Article> -> {
                            Resource.LocalSuccess(data = Articles(ArrayList(it.data.map { ArticlesItem(it.id, it.title, it.ingress, it.image, it.dateTime) })))
                        }
                        else -> {
                            Resource.LocalDataError(errorCode = it.errorCode as Int)
                        }
                    }
                }
            }
        }
    }

    fun insertLocalArticles(articles: Articles){
        viewModelScope.launch {
            wrapEspressoIdlingResource {
                val dbArticles = articles.articlesList.map { articlesItem ->
                    Article(
                        articlesItem.id,
                        articlesItem.title,
                        articlesItem.ingress,
                        articlesItem.image,
                        articlesItem.dateTime
                    )
                }
                dataRepositoryRepository.insertLocalArticles(dbArticles).collect {
                    // Data stored locally, do something if needed
                }
            }
        }
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = SingleEvent(error.description)
    }
}
