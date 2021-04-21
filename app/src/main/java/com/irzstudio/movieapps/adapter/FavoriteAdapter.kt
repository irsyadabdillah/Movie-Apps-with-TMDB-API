package com.irzstudio.movieapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.irzstudio.movieapps.R
import com.irzstudio.movieapps.listener.OnClickListener
import com.irzstudio.movieapps.model.datailfilm.DetailResponse
import com.irzstudio.movieapps.model.favorite.FavoriteEntity
import com.irzstudio.movieapps.util.Constant
import kotlinx.android.synthetic.main.list_favorite.view.*

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private var list: MutableList<FavoriteEntity> = mutableListOf()

    var onClickListener: OnClickListener? =null

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(favoriteEntity: FavoriteEntity){

            itemView.setOnClickListener {
                onClickListener?.onClickFav(favoriteEntity)
            }

            Glide.with(itemView)
                .load("${Constant.URL_IMAGE}${favoriteEntity.posterPath}")
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(itemView.iv_detail_fav)

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


}