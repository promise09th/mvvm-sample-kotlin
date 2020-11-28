package com.promise09th.mvvmkotlin.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.promise09th.mvvmkotlin.R
import com.promise09th.mvvmkotlin.databinding.FragmentMainBinding
import com.promise09th.mvvmkotlin.presentation.ViewModelFactory
import com.promise09th.mvvmkotlin.presentation.main.recyclerview.BookAdapter
import com.promise09th.mvvmkotlin.presentation.main.viewmodel.MainViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class MainFragment : DaggerFragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val mainViewModel by activityViewModels<MainViewModel> { viewModelFactory }

    private lateinit var adapter: BookAdapter
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = BookAdapter(mainViewModel)
        binding.viewModel = mainViewModel
        binding.searchResultRecyclerview.adapter = adapter
        binding.searchResultRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!binding.searchResultRecyclerview.canScrollVertically(1)) {
                    mainViewModel.moreFetchBooks()
                }
            }
        })
        binding.lifecycleOwner = viewLifecycleOwner

        setupClicked()
    }

    fun setupClicked() {
        with(mainViewModel) {
            this.searchResultItemClicked.observe(viewLifecycleOwner, Observer { event ->
                event.contentIfNotHandled?.let {
                    findNavController().navigate(R.id.navigation_detail_fragment)
                }
            })

            errorToast.observe(viewLifecycleOwner, Observer { event ->
                event.contentIfNotHandled?.let {
                    showErrorToast()
                }
            })

            completeToast.observe(viewLifecycleOwner, Observer { event ->
                event.contentIfNotHandled?.let {
                    showCompleteToast()
                }
            })

            this.searchBooks.observe(viewLifecycleOwner, Observer {
                it?.let {
                    adapter.submitList(it)
                }
            })
        }
    }

    private fun showCompleteToast() {
        Toast.makeText(activity, R.string.fetch_complete, Toast.LENGTH_SHORT).show()
    }

    private fun showErrorToast() {
        Toast.makeText(activity, R.string.fetch_error, Toast.LENGTH_SHORT).show()
    }
}
