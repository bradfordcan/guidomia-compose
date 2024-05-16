package com.exam.jp.guidomia.framework.di

import android.app.Application
import com.exam.jp.guidomia.core.repository.CarRepository
import com.exam.jp.guidomia.framework.db.RoomCarDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideRepository(app: Application) = CarRepository(RoomCarDataSource(app))
}