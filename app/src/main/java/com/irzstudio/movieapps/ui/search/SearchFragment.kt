package com.irzstudio.movieapps.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.irzstudio.movieapps.R
import com.irzstudio.movieapps.adapter.SearchAdapter
import com.irzstudio.movieapps.listener.OnClickItemSearch
import com.irzstudio.movieapps.model.search.SearchMovie
import com.irzstudio.movieapps.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by activityViewModels()

    private val adapterSearch: SearchAdapter by lazy {
        SearchAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListSearch()
        searchMovie()
        observeSearch()

    }

    private fun searchMovie() {
        search_movie.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search_movie.clearFocus()
                viewModel.requestMovieQuery(query)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun observeSearch() {
        viewModel.searchMovieList.observe(viewLifecycleOwner, {
            adapterSearch.setDataSearch(it)
        })
    }

    private fun setListSearch() {
        rv_movie_search.setHasFixedSize(true)
        rv_movie_search.adapter = adapterSearch
        adapterSearch.onClickItemSearch = object : OnClickItemSearch {
            override fun onClick(searchMovie: SearchMovie) {
                navigationToDetail(searchMovie)
            }
        }
    }

    private fun navigationToDetail(searchMovie: SearchMovie){
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("id", searchMovie.id)
        startActivity(intent)
    }

}




