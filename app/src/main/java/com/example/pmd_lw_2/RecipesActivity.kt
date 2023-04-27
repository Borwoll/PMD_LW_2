package com.example.pmd_lw_2

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.pmd_lw_2.fragments.RecipesFragment


class RecipesActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recipesFragment = RecipesFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, recipesFragment)
        fragmentTransaction.commit()
    }
}