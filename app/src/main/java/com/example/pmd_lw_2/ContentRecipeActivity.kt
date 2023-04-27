package com.example.pmd_lw_2
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.pmd_lw_2.fragments.ContentFragment

class ContentRecipeActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contentFragment = ContentFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, contentFragment)
        fragmentTransaction.commit()
    }
}