package com.promise09th.mvvmkotlin.presentation.main

import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.promise09th.mvvmkotlin.R
import com.promise09th.mvvmkotlin.presentation.ViewModelFactory
import com.promise09th.mvvmkotlin.presentation.main.viewmodel.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val mainViewModel by viewModels<MainViewModel> { viewModelFactory }

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        toolbar.setTitleTextColor(getColor(R.color.color_423630))

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val navGraph = navController.navInflater.inflate(R.navigation.main_navigation)
        navController.setGraph(navGraph, Bundle())
    }

    override fun onBackPressed() {
        if (!navController.popBackStack()) {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.maxWidth = Int.MAX_VALUE
        searchView.queryHint = getString(R.string.search_view_hint)
        setSearchViewColor(searchView)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(input: String): Boolean {
                mainViewModel.fetchBooks(input)
                searchView.clearFocus()
                if (R.id.navigation_main_fragment != navController.getCurrentDestination()?.id) {
                    onBackPressed()
                }
                return false
            }

            override fun onQueryTextChange(input: String): Boolean = false
        })
        return true
    }

    private fun setSearchViewColor(searchView: SearchView) {
        try {
            val searchIcon = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_button)
            searchIcon.setColorFilter(getColor(R.color.color_423630))

            val cancelIcon =searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
            cancelIcon.setColorFilter(getColor(R.color.color_423630))

            val searchAutoComplete = searchView.findViewById<SearchView.SearchAutoComplete>(androidx.appcompat.R.id.search_src_text)
            searchAutoComplete.setHintTextColor(getColor(R.color.color_423630))
            searchAutoComplete.setTextColor(getColor(R.color.color_423630))
        } catch (ignored: Throwable) {}
    }
}
