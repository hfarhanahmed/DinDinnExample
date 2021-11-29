package com.sevenpeakssoftware.farhan.ui.component.recipes.adapter

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sevenpeakssoftware.farhan.R
import com.sevenpeakssoftware.farhan.data.dto.recipes.RecipesItem
import com.sevenpeakssoftware.farhan.databinding.RecipeItemBinding
import com.sevenpeakssoftware.farhan.ui.base.listeners.RecyclerItemListener

/**
 * Created by FarhanAhmed
 */

class RecipeViewHolder(private val itemBinding: RecipeItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(recipesItem: RecipesItem, recyclerItemListener: RecyclerItemListener) {
        itemBinding.tvCaption.text = recipesItem.description
        itemBinding.tvName.text = recipesItem.name
        Picasso.get().load(recipesItem.thumb).placeholder(R.drawable.ic_healthy_food).error(R.drawable.ic_healthy_food).into(itemBinding.ivRecipeItemImage)
        itemBinding.rlRecipeItem.setOnClickListener { recyclerItemListener.onItemSelected(recipesItem) }
    }
}

