package com.irzstudio.movieapps.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.irzstudio.movieapps.adapter.CastAdapter
import com.irzstudio.movieapps.util.Constant.URL_IMAGE
import com.irzstudio.movieapps.databinding.ActivityDetailBinding
import com.irzstudio.movieapps.model.datailfilm.DetailResponse
import com.irzstudio.movieapps.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_detail.*
import java.util.*

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    private val viewModel: DetailViewModel by lazy {
        ViewModelProviders.of(this).get(DetailViewModel::class.java)
    }
    private val adapterCast: CastAdapter by lazy {
        CastAdapter()
    }
    private val id: Int by lazy {
        intent.getIntExtra("id", 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        navigationBack()
        setListCast()
        observeCast()
        observeDetailMovie()
        observeIsFavorited()

        viewModel.requestCast(id)
        viewModel.requestDetailMovie(id)
    }

    private fun shareMovie(detailResponse: DetailResponse){
        btn_share.setOnClickListener {
            val url = detailResponse.url
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, url)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }

    private fun addFavoriteMovie(){
        cb_fav.setOnCheckedChangeListener { checkBox, isChecked ->
            if (isChecked){
                viewModel.saveMovie()
                Toast.makeText(this, "Movie added to favorite", Toast.LENGTH_SHORT).show()
            }else{
                viewModel.removeMovie()
                Toast.makeText(this, "Movie removed from favorite", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun observeDetailMovie(){
        viewModel.detailResponse.observe(this, {
            loadPoster(it)
            loadDetail(it)
            shareMovie(it)
            convertDuration(it)
            viewModel.checkFavMovie()
        })
    }

    private fun observeIsFavorited(){
        viewModel.isFavorited.observe(this,{
            cb_fav.isChecked = it
            addFavoriteMovie()
        })
    }

    private fun observeCast(){
        viewModel.castResponseList.observe(this, {
            adapterCast.setDataCast(it)
        })
    }

    private fun loadPoster(detailResponse: DetailResponse) {
        Glide.with(this)
            .load("$URL_IMAGE${detailResponse.backdropPath}")
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(binding.ivPoster)
    }

    private fun loadDetail(detailResponse: DetailResponse) {
        binding.txtTitleDetail.text = detailResponse.originalTitle
        binding.txtReleaseDetail.text = detailResponse.releaseDate
        binding.txtGenreDetail.text = detailResponse.genres.map { it.nameGenre }.joinToString("-")
        binding.txtDescriptionDetail.text = detailResponse.description
    }

    private fun convertDuration(detailResponse: DetailResponse) {
        val date = detailResponse.duration
        val hours = date / 60
        val minutes = date % 60
        val duration = String.format("%dh:%02dm", hours, minutes)
        binding.txtDurationDetail.text = duration
    }

    private fun setListCast(){
        binding.rvCast.setHasFixedSize(true)
        binding.rvCast.adapter = adapterCast
    }

    private fun navigationBack(){
        iv_back.setOnClickListener {
            finish()
        }
    }

}