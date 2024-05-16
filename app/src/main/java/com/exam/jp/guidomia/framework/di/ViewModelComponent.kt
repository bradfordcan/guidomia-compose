package com.exam.jp.guidomia.framework.di

import com.exam.jp.guidomia.ui.cars.CarsViewModel
import dagger.Component

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class, SharedPreferencesModule::class])
interface ViewModelComponent {
    fun inject(homeViewModel: CarsViewModel)
}