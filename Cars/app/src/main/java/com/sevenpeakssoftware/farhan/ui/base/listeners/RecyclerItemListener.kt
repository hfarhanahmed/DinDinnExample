package com.sevenpeakssoftware.farhan.ui.base.listeners

import com.sevenpeakssoftware.farhan.data.dto.recipes.RecipesItem

/**
 * Created by FarhanAhmed
 */

interface RecyclerItemListener {
    fun onItemSelected(recipe : RecipesItem)
}
