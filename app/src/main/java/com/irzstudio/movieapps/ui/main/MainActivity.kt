package com.irzstudio.movieapps.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.irzstudio.movieapps.R
import com.irzstudio.movieapps.ui.favorite.FavoriteFragment
import com.irzstudio.movieapps.ui.home.HomeFragment
import com.irzstudio.movieapps.ui.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationToHome()
        initMenuBar()

    }

    private fun initMenuBar(){
        bubble_tab_bar.addBubbleListener {
            when(it){
                R.id.home -> navigationToHome()
                R.id.favorite -> navigationToFavorite()
                R.id.search -> navigationToSearch()
            }
        }
    }

    private fun navigationToHome() {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, HomeFragment()).commit()
    }

    private fun navigationToFavorite() {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, FavoriteFragment()).commit()
    }

    private fun navigationToSearch(){
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, SearchFragment()).commit()
    }

}