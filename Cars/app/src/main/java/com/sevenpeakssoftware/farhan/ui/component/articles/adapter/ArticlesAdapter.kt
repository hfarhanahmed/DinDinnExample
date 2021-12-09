package com.sevenpeakssoftware.farhan.ui.component.articles.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sevenpeakssoftware.farhan.data.dto.articles.ArticlesItem
import com.sevenpeakssoftware.farhan.databinding.ArticleItemBinding
import com.sevenpeakssoftware.farhan.ui.base.listeners.RecyclerItemListener
import com.sevenpeakssoftware.farhan.ui.component.articles.ArticlesListViewModel

/**
 * Created by FarhanAhmed
 */

class ArticlesAdapter(private val articlesListViewModel: ArticlesListViewModel, private val recipes: List<ArticlesItem>) : RecyclerView.Adapter<ArticleViewHolder>() {

    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(recipe: ArticlesItem) {
            // Handle on click event using ArticlesListViewModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemBinding = ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(recipes[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}

