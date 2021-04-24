package com.irzstudio.movieapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.irzstudio.movieapps.R
import com.irzstudio.movieapps.listener.OnClickItemFavorite
import com.irzstudio.movieapps.model.favorite.FavoriteEntity
import com.irzstudio.movieapps.util.Constant
import kotlinx.android.synthetic.main.list_favorite.view.*

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private var list: MutableList<FavoriteEntity> = mutableListOf()
    var onClickItemFavorite: OnClickItemFavorite?= null

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(favoriteEntity: FavoriteEntity) {

            itemView.setOnClickListener {
                onClickItemFavorite?.onClick(favoriteEntity)
            }

            itemView.cb_fav_list.setOnClickListener {
                onClickItemFavorite?.onClickFav(favoriteEntity)
            }

            Glide.with(itemView)
                .load("${Constant.URL_IMAGE}${favoriteEntity.posterPath}")
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(itemView.iv_detail_fav)
            itemView.txt_title_favorite.text = favoriteEntity.originalTitle
            itemView.txt_year_favorite.text = favoriteEntity.releaseDate
            itemView.txt_genre_favorite.text = favoriteEntity.genres.map { it.name }.joinToString { "-" }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_favorite, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setDataFavorite(data: List<FavoriteEntity>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}