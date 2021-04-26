package com.irzstudio.movieapps.screen.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.irzstudio.movieapps.R
import com.irzstudio.movieapps.adapter.SearchAdapter
import com.irzstudio.movieapps.listener.OnClickItemSearch
import com.irzstudio.movieapps.model.search.SearchMovie
import com.irzstudio.movieapps.screen.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() {


    private lateinit var viewModel: SearchViewModel
    private val adapterSearch: SearchAdapter by lazy {
        SearchAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

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




