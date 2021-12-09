package com.sevenpeakssoftware.farhan.ui.component.articles.adapter

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sevenpeakssoftware.farhan.R
import com.sevenpeakssoftware.farhan.data.dto.articles.ArticlesItem
import com.sevenpeakssoftware.farhan.databinding.ArticleItemBinding
import com.sevenpeakssoftware.farhan.ui.base.listeners.RecyclerItemListener
import com.sevenpeakssoftware.farhan.utils.CommonUtils

/**
 * Created by FarhanAhmed
 */

class ArticleViewHolder(private val itemBinding: ArticleItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(articlesItem: ArticlesItem, recyclerItemListener: RecyclerItemListener) {
        itemBinding.tvTitle.text = articlesItem.title
        itemBinding.tvDatetime.text = CommonUtils.formatDate(articlesItem.dateTime,itemBinding.root.context)
        itemBinding.tvIngress.text = articlesItem.ingress
        Picasso.get().load(articlesItem.image).placeholder(R.drawable.ic_car_large).error(R.drawable.ic_car_large).into(itemBinding.ivRecipeItemImage)
        itemBinding.rlRecipeItem.setOnClickListener { recyclerItemListener.onItemSelected(articlesItem) }
    }
}

