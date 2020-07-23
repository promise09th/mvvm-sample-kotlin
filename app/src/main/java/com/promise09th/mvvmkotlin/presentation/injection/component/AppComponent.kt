package com.promise09th.mvvmkotlin.presentation.injection.component

import android.app.Application
import com.promise09th.mvvmkotlin.MvvmApplication
import com.promise09th.mvvmkotlin.presentation.injection.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

// Reference : https://android.jlelse.eu/7-steps-to-implement-dagger-2-in-android-dabc16715a3a
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityModule::class,
    AppModule::class,
    DataModule::class,
    FragmentModule::class,
    PresentationModule::class,
    RemoteModule::class
])

interface AppComponent : AndroidInjector<MvvmApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    override fun inject(mvvmApplication: MvvmApplication)
}