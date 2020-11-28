package com.promise09th.mvvmkotlin.presentation.injection.module

import com.promise09th.mvvmkotlin.presentation.main.DetailFragment
import com.promise09th.mvvmkotlin.presentation.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeSearchMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailFragment(): DetailFragment
}