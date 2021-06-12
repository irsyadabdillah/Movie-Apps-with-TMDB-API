package com.irzstudio.movieapps.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.irzstudio.movieapps.R
import com.irzstudio.movieapps.adapter.FavoriteAdapter
import com.irzstudio.movieapps.listener.OnClickItemFavorite
import com.irzstudio.movieapps.model.favorite.FavoriteEntity
import com.irzstudio.movieapps.ui.detail.DetailActivity
import com.irzstudio.movieapps.ui.detail.DetailViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val viewModel: FavoriteViewModel by viewModel()

    private val adapterFavorite: FavoriteAdapter by lazy {
        FavoriteAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListFavorite()
        observeFavoriteList()
        viewModel.loadFovoriteData()

    }

    private fun observeFavoriteList() {
        viewModel.favoriteEntityList.observe(viewLifecycleOwner, {
            adapterFavorite.setDataFavorite(it)
        })
    }

    private fun setListFavorite() {
        rv_favorite.setHasFixedSize(true)
        rv_favorite.adapter = adapterFavorite
        adapterFavorite.onClickItemFavorite = object : OnClickItemFavorite {
            override fun onClick(favoriteEntity: FavoriteEntity) {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra("id", favoriteEntity.id)
                startActivity(intent)
            }

            override fun onClickFav(favoriteEntity: FavoriteEntity) {
                viewModel.removeMovie(favoriteEntity.id)
                Toast.makeText(activity, "Movie removed from favorite", Toast.LENGTH_SHORT).show()
                observeFavoriteList()
            }
        }
    }


}