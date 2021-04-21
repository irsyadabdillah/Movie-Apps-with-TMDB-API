package com.irzstudio.movieapps.screen.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemChangeListener
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.irzstudio.movieapps.util.Constant.URL_IMAGE
import com.irzstudio.movieapps.R
import com.irzstudio.movieapps.databinding.FragmentHomeBinding
import com.irzstudio.movieapps.model.discover.Discover
import com.irzstudio.movieapps.screen.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        viewModel.requestDiscover()
        observeDataDiscover()

    }

    private fun observeDataDiscover() {
        viewModel.discoverResponseList.observe(viewLifecycleOwner, {
            loadImageList(it)
            navigationToDetailMovie(it)
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
                txt_title_discover.text = data[position].title
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


}