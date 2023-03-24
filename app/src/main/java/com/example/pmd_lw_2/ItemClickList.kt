package com.example.pmd_lw_2

import android.view.View
import android.content.Intent

interface CategoryItemClickList {
    public fun onCategoryClick(slug: String, description: String, view: View)
}

class Recipes: CategoryItemClickList {
    override fun onCategoryClick(slug: String, description: String, view: View) {
        val intent = Intent(view.context, RecipesActivity::class.java)
        intent.putExtra("slug", slug)
        intent.putExtra("description", description)
        view.context.startActivity(intent)
    }
}

class ContentRecipe: CategoryItemClickList {
    override fun onCategoryClick(slug: String, description: String, view: View) {
        val intent = Intent(view.context, ContentRecipeActivity::class.java)
        intent.putExtra("slug", slug)
        intent.putExtra("description", description)
        view.context.startActivity(intent)
    }
}

enum class ItemType {
    Recipes, ContentRecipe
}

class ItemClickListFactory {
    fun makeItemClickList(type: ItemType): CategoryItemClickList? {
        return when(type) {
            ItemType.Recipes -> Recipes()
            ItemType.ContentRecipe -> ContentRecipe()
            else -> null
        }
    }
}