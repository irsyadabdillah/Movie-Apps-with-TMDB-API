package com.irzstudio.movieapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.irzstudio.movieapps.R
import com.irzstudio.movieapps.listener.OnClickItemTrending
import com.irzstudio.movieapps.model.trending.PosterTrending
import com.irzstudio.movieapps.util.Constant
import kotlinx.android.synthetic.main.list_trending.view.*

class TrendingAdapter: RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {

    private var list: MutableList<PosterTrending> = mutableListOf()
    var onClickListener: OnClickItemTrending? =null

    inner class TrendingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(posterTrending: PosterTrending){

            itemView.setOnClickListener {
                onClickListener?.onClick(posterTrending)
            }

            Glide.with(itemView)
                .load("${Constant.URL_IMAGE}${posterTrending.posterPath}")
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(itemView.iv_trending)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_trending, parent, false)
        return TrendingViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setDataTrending(data: List<PosterTrending>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}