package com.exam.jp.guidomia.core.repository

import com.exam.jp.guidomia.core.data.Car

class CarRepository(private val dataSource: CarDataSource) {

    suspend fun addCar(car: Car) = dataSource.add(car)

    suspend fun getCar(id: Long) = dataSource.get(id)

    suspend fun getAllCars() = dataSource.getAll()

    suspend fun deleteCar(car: Car) = dataSource.delete(car)

}