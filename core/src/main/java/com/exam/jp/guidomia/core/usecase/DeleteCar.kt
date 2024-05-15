package com.exam.jp.guidomia.core.usecase

import com.exam.jp.guidomia.core.data.Car
import com.exam.jp.guidomia.core.repository.CarRepository

class DeleteCar(private val repository: CarRepository) {
    suspend operator fun invoke(car: Car) = repository.deleteCar(car)
}