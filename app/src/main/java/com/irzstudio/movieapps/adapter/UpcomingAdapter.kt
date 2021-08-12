package com.irzstudio.movieapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.irzstudio.movieapps.R
import com.irzstudio.movieapps.listener.OnClickItemUpcoming
import com.irzstudio.movieapps.model.upcoming.PosterUpcoming
import com.irzstudio.movieapps.util.Constant
import kotlinx.android.synthetic.main.list_upcoming.view.*

class UpcomingAdapter: RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder>() {

    private var list: MutableList<PosterUpcoming> = mutableListOf()
    var onClickListener: OnClickItemUpcoming? =null

    inner class UpcomingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(posterUpcoming: PosterUpcoming){

            itemView.setOnClickListener {
                onClickListener?.onClick(posterUpcoming)
            }

            Glide.with(itemView)
                .load("${Constant.URL_IMAGE}${posterUpcoming.posterPath}")
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(itemView.iv_upcoming)
            itemView.txt_title_upcoming.text = posterUpcoming.title
            itemView.txt_year_upcoming.text = posterUpcoming.releaseDate

            val rating = posterUpcoming.rating
            itemView.rating_bar.numStars = 5
            itemView.rating_bar.stepSize = 0.5f
            itemView.rating_bar.rating = rating / 2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_upcoming, parent, false)
        return UpcomingViewHolder(view)
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return if (list.size >= 8){
            8
        }else{
            list.size
        }

    }

    fun setDataUpcoming(data: List<PosterUpcoming>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}