package com.promise09th.mvvmkotlin.presentation.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.promise09th.mvvmkotlin.domain.book.GetBooksUseCase
import com.promise09th.mvvmkotlin.presentation.Event
import com.promise09th.mvvmkotlin.presentation.model.Book
import com.promise09th.mvvmkotlin.presentation.model.BookInfo
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class MainViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase
): ViewModel() {

    val searchBooks: MutableLiveData<ArrayList<Book>> = MutableLiveData()
    val selectedBooks: MutableLiveData<Book> = MutableLiveData()
    val totalBookCount: MutableLiveData<Int> = MutableLiveData(0)
    val currentBookCount: MutableLiveData<Int> = MutableLiveData(0)

    val searchResultItemClicked: MutableLiveData<Event<Book>> = MutableLiveData()

    val errorToast: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val completeToast: MutableLiveData<Event<Boolean>> = MutableLiveData()

    private val disposables = CompositeDisposable()

    private var nowInitialQuerying = false
    private var isEnd = false
    private var currentQuery = ""
    private var currentPage = 1

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    fun onClickItem(book: Book) {
        selectedBooks.value = book
        searchResultItemClicked.setValue(Event(book))
    }

    private fun updateSearchBooks(bookInfo: BookInfo, lists: List<Book>, currentPage: Int) {
        isEnd = bookInfo.is_end
        totalBookCount.value = bookInfo.pageable_count

        lists.sortedWith(
            Comparator { t1: Book, t2: Book ->
                t2.datetime.compareTo(t1.datetime)
            })
        when (currentPage) {
            1 -> {
                currentBookCount.value = lists.size
                searchBooks.value = ArrayList(lists)
            }
            else -> {
                searchBooks.value?.let {
                    val newArrayList = ArrayList<Book>()
                    newArrayList.addAll(it)
                    newArrayList.addAll(lists)
                    currentBookCount.value = newArrayList.size
                    searchBooks.value = newArrayList
                }
            }
        }
    }

    fun resetSearchResult(query: String) {
        currentQuery = query
        currentPage = 1
        isEnd = false
        searchBooks.value = ArrayList()
    }

    fun fetchBooks(query: String) {
        resetSearchResult(query)

        nowInitialQuerying = true
        disposables.add(getBooksUseCase.params(query, currentPage).subscribe(
            {
                nowInitialQuerying = false
                updateSearchBooks(it.info, it.books, currentPage)
            },
            { errorToast.value = Event(true) }
        ))
    }

    fun moreFetchBooks() {
        if (nowInitialQuerying || isEnd) {
            return
        }

        disposables.add(getBooksUseCase.params(currentQuery, ++currentPage).subscribe(
            { updateSearchBooks(it.info, it.books, currentPage)},
            { errorToast.value = Event(true) }
        ))
    }
}