package com.exam.jp.guidomia.core.repository

import com.exam.jp.guidomia.core.data.Car

interface CarDataSource {
    suspend fun add(car: Car)

    suspend fun get(id: Long): Car?

    suspend fun getAll(): List<Car>

    suspend fun delete(car: Car)
}