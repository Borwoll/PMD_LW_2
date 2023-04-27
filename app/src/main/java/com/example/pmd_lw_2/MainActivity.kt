package com.example.pmd_lw_2

import android.os.Bundle
import android.os.StrictMode
import androidx.fragment.app.FragmentActivity
import com.example.pmd_lw_2.fragments.CategoryFragment

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val categoryFragment = CategoryFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, categoryFragment)
        fragmentTransaction.commit()
    }
}