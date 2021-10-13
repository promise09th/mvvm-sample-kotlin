package com.promise09th.mvvmkotlin.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.promise09th.mvvmkotlin.domain.usecase.book.GetBooksUseCase
import com.promise09th.mvvmkotlin.presentation.Event
import com.promise09th.mvvmkotlin.presentation.mapper.mapToBookInfoModel
import com.promise09th.mvvmkotlin.presentation.mapper.mapToBookModel
import com.promise09th.mvvmkotlin.presentation.model.BookInfoModel
import com.promise09th.mvvmkotlin.presentation.model.BookModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase
): ViewModel() {

    private val _searchBooks: MutableLiveData<ArrayList<BookModel>> = MutableLiveData()
    val searchBooks: LiveData<ArrayList<BookModel>> = _searchBooks

    private val _selectedBooks: MutableLiveData<BookModel> = MutableLiveData()
    val selectedBooks: LiveData<BookModel> = _selectedBooks

    private val _totalBookCount: MutableLiveData<Int> = MutableLiveData(0)
    val totalBookCount: LiveData<Int> = _totalBookCount

    private val _currentBookCount: MutableLiveData<Int> = MutableLiveData(0)
    val currentBookCount: LiveData<Int> = _currentBookCount

    private val _searchResultItemClicked: MutableLiveData<Event<BookModel>> = MutableLiveData()
    val searchResultItemClicked: LiveData<Event<BookModel>> = _searchResultItemClicked

    private val _errorToast: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val errorToast: LiveData<Event<Boolean>> = _errorToast

    private val _completeToast: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val completeToast: LiveData<Event<Boolean>> = _completeToast

    private val disposables = CompositeDisposable()

    private var nowInitialQuerying = false
    private var isEnd = false
    private var currentQuery = ""
    private var currentPage = 1

    private fun Disposable.bind() {
        disposables.add(this)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    fun onClickItem(book: BookModel) {
        _selectedBooks.value = book
        _searchResultItemClicked.setValue(Event(book))
    }

    private fun updateSearchBooks(bookInfo: BookInfoModel, lists: List<BookModel>, currentPage: Int) {
        isEnd = bookInfo.is_end
        _totalBookCount.value = bookInfo.pageable_count

        lists.sortedWith { t1: BookModel, t2: BookModel -> t2.datetime.compareTo(t1.datetime)
}
        when (currentPage) {
            1 -> {
                _currentBookCount.value = lists.size
                _searchBooks.value = ArrayList(lists)
            }
            else -> {
                searchBooks.value?.let {
                    val newArrayList = ArrayList<BookModel>()
                    newArrayList.addAll(it)
                    newArrayList.addAll(lists)
                    _currentBookCount.value = newArrayList.size
                    _searchBooks.value = newArrayList
                }
            }
        }
    }

    private fun resetSearchResult(query: String) {
        currentQuery = query
        currentPage = 1
        isEnd = false
        _searchBooks.value = ArrayList()
    }

    fun fetchBooks(query: String) {
        resetSearchResult(query)

        nowInitialQuerying = true
        getBooksUseCase(query, currentPage)
            .subscribe(
                {
                    nowInitialQuerying = false
                    updateSearchBooks(
                        it.info.mapToBookInfoModel(),
                        it.books.map { it.mapToBookModel() },
                        currentPage
                    )
                },
                { _errorToast.value = Event(true) }
            ).bind()
    }

    fun moreFetchBooks() {
        if (nowInitialQuerying || isEnd) {
            return
        }

        getBooksUseCase(currentQuery, ++currentPage)
            .subscribe(
                {
                    updateSearchBooks(
                        it.info.mapToBookInfoModel(),
                        it.books.map { it.mapToBookModel() },
                        currentPage
                    )
                },
                { _errorToast.value = Event(true) }
            ).bind()
    }
}