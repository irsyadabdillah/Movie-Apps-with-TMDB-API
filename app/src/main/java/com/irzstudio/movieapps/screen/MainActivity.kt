package com.irzstudio.movieapps.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.irzstudio.movieapps.R
import com.irzstudio.movieapps.databinding.ActivityMainBinding
import com.irzstudio.movieapps.screen.favorite.FavoriteFragment
import com.irzstudio.movieapps.screen.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        navigationToHome()
        initMenuBar()

    }

    private fun initMenuBar(){
        bubble_tab_bar.addBubbleListener {
            if (it == R.id.home) {
                navigationToHome()
            } else {
                navigationToFavorite()
            }
        }
    }

    private fun navigationToHome() {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, HomeFragment()).commit()
    }

    private fun navigationToFavorite() {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, FavoriteFragment()).commit()
    }
}