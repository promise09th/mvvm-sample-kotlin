package com.promise09th.mvvmkotlin.presentation.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.promise09th.mvvmkotlin.domain.model.BookDomain
import com.promise09th.mvvmkotlin.domain.model.BookInfoDomain
import com.promise09th.mvvmkotlin.domain.model.BookListDomain
import com.promise09th.mvvmkotlin.domain.usecase.book.GetBooksUseCase
import com.promise09th.mvvmkotlin.presentation.mapper.mapToBookModel
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.TestSubscriber
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock lateinit var getBooksUseCase: GetBooksUseCase

    @InjectMocks lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        setUpRxSchedulers()
    }

    private fun setUpRxSchedulers() {
        RxJavaPlugins.setInitIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun fetchBooksTest() {
        val bookListDomain = BookListDomain(
            info = BookInfoDomain(
                total_count = 118,
                pageable_count = 113,
                is_end = false
            ),
            books = arrayListOf(
                BookDomain(title = "Who? K-pop IU(아이유)(양장본 HardCover)"),
                BookDomain(title = "이상한 나라의 아이유"),
                BookDomain(title = "아이유 피아노 연주&반주곡집"),
            )
        )

        Mockito.`when`(getBooksUseCase("아이유", 1)).thenReturn(Single.just(bookListDomain))

        mainViewModel.fetchBooks("아이유")

        val testSubscriber: TestSubscriber<BookListDomain> = TestSubscriber.create()
        getBooksUseCase("아이유", 1).toFlowable().subscribe(testSubscriber)

        val booksResult = bookListDomain.books.map { it.mapToBookModel() }
        val booksCountResult = bookListDomain.books.size

        assertEquals(mainViewModel.searchBooks.value, booksResult)
        assertEquals(mainViewModel.currentBookCount.value, booksCountResult)
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }
}