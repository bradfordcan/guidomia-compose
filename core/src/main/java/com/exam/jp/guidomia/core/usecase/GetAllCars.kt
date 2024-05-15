package com.exam.jp.guidomia.core.usecase

import com.exam.jp.guidomia.core.repository.CarRepository

class GetAllCars(private val repository: CarRepository) {
    suspend operator fun invoke() = repository.getAllCars()
}