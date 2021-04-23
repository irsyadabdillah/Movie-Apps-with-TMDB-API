package com.irzstudio.movieapps.screen.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.irzstudio.movieapps.R
import com.irzstudio.movieapps.adapter.FavoriteAdapter
import com.irzstudio.movieapps.adapter.TrendingAdapter
import com.irzstudio.movieapps.listener.OnClickItemFavorite
import com.irzstudio.movieapps.model.favorite.FavoriteEntity
import com.irzstudio.movieapps.screen.detail.DetailViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {

    private lateinit var viewModel: FavoriteViewModel

    private val adapterFavorite: FavoriteAdapter by lazy {
        FavoriteAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FavoriteViewModel::class.java)

        viewModel.loadFovoriteData()
        setListFavorite()
        observeFavoriteList()

    }

    private fun observeFavoriteList(){
        viewModel.favoriteEntity.observe(viewLifecycleOwner, {
            adapterFavorite.setDataFavorite(it)
        })
    }

    private fun setListFavorite(){
        rv_favorite.setHasFixedSize(true)
        rv_favorite.adapter = adapterFavorite
        adapterFavorite.onClickItemFavorite = object : OnClickItemFavorite {
            override fun onClick(favoriteEntity: FavoriteEntity) {
                TODO("Not yet implemented")
            }

            override fun onClickFav(favoriteEntity: FavoriteEntity) {
                TODO("Not yet implemented")
            }

        }


    }
}