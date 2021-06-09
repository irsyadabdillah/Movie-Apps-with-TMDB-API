package com.irzstudio.movieapps.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemChangeListener
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.irzstudio.movieapps.R
import com.irzstudio.movieapps.adapter.TrendingAdapter
import com.irzstudio.movieapps.adapter.UpcomingAdapter
import com.irzstudio.movieapps.databinding.FragmentHomeBinding
import com.irzstudio.movieapps.listener.OnClickItemTrending
import com.irzstudio.movieapps.listener.OnClickItemUpcoming
import com.irzstudio.movieapps.model.discover.Discover
import com.irzstudio.movieapps.model.trending.PosterTrending
import com.irzstudio.movieapps.model.upcoming.PosterUpcoming
import com.irzstudio.movieapps.ui.detail.DetailActivity
import com.irzstudio.movieapps.util.Constant.URL_IMAGE
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    private val adapterTrending: TrendingAdapter by lazy {
        TrendingAdapter()
    }
    private val adapterUpcoming: UpcomingAdapter by lazy {
        UpcomingAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeDiscover()

        setListTrending()
        observeTrending()

        setListUpcoming()
        observeUpcoming()

        viewModel.requestDiscover()
        viewModel.requestTrending()
        viewModel.requestUpcoming()
    }

    private fun observeDiscover() {
        viewModel.discoverResponseList.observe(viewLifecycleOwner, {
            loadImageList(it)
            navigationToDetailMovie(it)
        })
    }

    private fun observeTrending() {
        viewModel.trendingResponseList.observe(viewLifecycleOwner, {
            adapterTrending.setDataTrending(it)
        })
    }

    private fun observeUpcoming() {
        viewModel.upcomingResponseList.observe(viewLifecycleOwner, {
            adapterUpcoming.setDataUpcoming(it)
        })
    }

    private fun loadImageList(data: ArrayList<Discover>) {

        val imageList = ArrayList<SlideModel>() // Create image list

        for (i in 0..5) {
            imageList.add(SlideModel("$URL_IMAGE${data[i].backdropPath}"))
        }

        image_slider.setImageList(imageList, ScaleTypes.CENTER_CROP)
        image_slider.setItemChangeListener(object : ItemChangeListener {
            override fun onItemChanged(position: Int) {
                txt_title_discover?.text = data[position].title
            }
        })
    }

    private fun navigationToDetailMovie(discover: ArrayList<Discover>) {
        image_slider.setItemClickListener(object : ItemClickListener {
            override fun onItemSelected(position: Int) {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra("id", discover[position].id)
                startActivity(intent)
            }
        })
    }

    private fun setListTrending() {
        rv_trending.setHasFixedSize(true)
        rv_trending.adapter = adapterTrending
        adapterTrending.onClickListener = object : OnClickItemTrending{
            override fun onClick(posterTrending: PosterTrending) {
                navigationToDetailTrending(posterTrending)
            }
        }
    }

    private fun setListUpcoming() {
        rv_upcoming.setHasFixedSize(true)
        rv_upcoming.adapter = adapterUpcoming
        adapterUpcoming.onClickListener = object : OnClickItemUpcoming{
            override fun onClick(posterUpcoming: PosterUpcoming) {
                navigationToDetailUpcoming(posterUpcoming)
            }
        }
    }

    private fun navigationToDetailTrending(posterTrending: PosterTrending){
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("id", posterTrending.id)
        startActivity(intent)
    }

    private fun navigationToDetailUpcoming(posterUpcoming: PosterUpcoming){
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("id", posterUpcoming.id)
        startActivity(intent)
    }

}