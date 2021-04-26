package com.irzstudio.movieapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.irzstudio.movieapps.R
import com.irzstudio.movieapps.listener.OnClickItemSearch
import com.irzstudio.movieapps.model.search.SearchMovie
import com.irzstudio.movieapps.model.search.SearchResponse
import com.irzstudio.movieapps.util.Constant
import kotlinx.android.synthetic.main.list_search.view.*
import kotlinx.android.synthetic.main.list_trending.view.*

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private var list: MutableList<SearchMovie> = mutableListOf()
    var onClickItemSearch: OnClickItemSearch?= null


    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(searchMovie: SearchMovie){

            itemView.setOnClickListener {
                onClickItemSearch?.onClick(searchMovie)
            }
            Glide.with(itemView)
                .load("${Constant.URL_IMAGE}${searchMovie.backdropPath}")
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(itemView.iv_search)
            itemView.txt_search_title.text = searchMovie.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_search, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setDataSearch(data: List<SearchMovie>){
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}