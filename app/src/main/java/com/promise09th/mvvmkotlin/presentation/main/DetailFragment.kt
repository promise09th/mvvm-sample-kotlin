package com.promise09th.mvvmkotlin.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.promise09th.mvvmkotlin.R
import com.promise09th.mvvmkotlin.databinding.FragmentDetailBinding
import com.promise09th.mvvmkotlin.presentation.ViewModelFactory
import com.promise09th.mvvmkotlin.presentation.main.viewmodel.MainViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DetailFragment : DaggerFragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val mainViewModel by activityViewModels<MainViewModel> { viewModelFactory }

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.book = mainViewModel.selectedBooks.value
        binding.lifecycleOwner = viewLifecycleOwner
    }
}