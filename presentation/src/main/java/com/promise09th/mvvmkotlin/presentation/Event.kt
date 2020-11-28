package com.promise09th.mvvmkotlin.presentation

class Event<T>(val content: T) {

    private var hasBeenHandled = false

    val contentIfNotHandled: T?
        get() = if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
}