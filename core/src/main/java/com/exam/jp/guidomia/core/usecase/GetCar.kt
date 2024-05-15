package com.exam.jp.guidomia.core.usecase

import com.exam.jp.guidomia.core.repository.CarRepository

class GetCar(private val repository: CarRepository) {
    suspend operator fun invoke(id: Long) = repository.getCar(id)
}