package com.promise09th.mvvmkotlin.presentation.common

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.promise09th.mvvmkotlin.R

@BindingAdapter("bind_image")
fun bindImage(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView)
            .load(url)
            .error(R.drawable.noimg)
            .into(imageView)
    }
}

@BindingAdapter("bind_date")
fun bindDate(textView: TextView, date: String?) {
    date?.let {
        textView.text
        textView.text = date.convertKakaoDateFormat()
    }
}

@BindingAdapter("bind_authors")
fun bindAuthors(textView: TextView, authors: Array<String>) {
    if (!authors.isNullOrEmpty()) {
        textView.text = authors.joinToString()
    } else {
        textView.text = "N/A"
    }
}

@BindingAdapter("book_count", "total_count")
fun bindBookCount(textView: TextView, currentCount: Int, totalCount: Int) {
    textView.text = "$currentCount / $totalCount"
}
