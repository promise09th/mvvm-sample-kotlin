package com.promise09th.mvvmkotlin.presentation.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.promise09th.mvvmkotlin.presentation.ViewModelFactory
import com.promise09th.mvvmkotlin.presentation.injection.scope.ViewModelKey
import com.promise09th.mvvmkotlin.presentation.main.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PresentationModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory?): ViewModelProvider.Factory?

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    protected abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}