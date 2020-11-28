package com.promise09th.mvvmkotlin.presentation.main.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.promise09th.mvvmkotlin.databinding.ListItemViewBinding
import com.promise09th.mvvmkotlin.presentation.main.viewmodel.MainViewModel
import com.promise09th.mvvmkotlin.presentation.model.BookModel

class BookHolder(
    var binding: ListItemViewBinding,
    val mainViewModel: MainViewModel
) : RecyclerView.ViewHolder(binding.getRoot()) {

    fun bind(book: BookModel) {
        binding.setViewModel(mainViewModel)
        binding.book = book
    }
}