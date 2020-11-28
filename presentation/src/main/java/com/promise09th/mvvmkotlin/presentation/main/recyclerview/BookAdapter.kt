package com.promise09th.mvvmkotlin.presentation.main.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.promise09th.mvvmkotlin.databinding.ListItemViewBinding
import com.promise09th.mvvmkotlin.presentation.main.viewmodel.MainViewModel
import com.promise09th.mvvmkotlin.presentation.model.BookModel

class BookAdapter(private val mainViewModel: MainViewModel) : ListAdapter<BookModel, BookHolder>(object : DiffUtil.ItemCallback<BookModel>() {
    override fun areItemsTheSame(oldItem: BookModel, newItem: BookModel) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: BookModel, newItem: BookModel) =
        oldItem == newItem
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val binding: ListItemViewBinding = ListItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return BookHolder(binding, mainViewModel)
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        holder.bind(getItem(position))
    }
}