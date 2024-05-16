package com.exam.jp.guidomia.framework.di

import com.exam.jp.guidomia.core.repository.CarRepository
import com.exam.jp.guidomia.core.usecase.AddCar
import com.exam.jp.guidomia.core.usecase.DeleteCar
import com.exam.jp.guidomia.core.usecase.GetAllCars
import com.exam.jp.guidomia.core.usecase.GetCar
import com.exam.jp.guidomia.framework.UseCases
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun getUseCases(repository: CarRepository) = UseCases(
        AddCar(repository),
        GetAllCars(repository),
        GetCar(repository),
        DeleteCar(repository)
    )
}