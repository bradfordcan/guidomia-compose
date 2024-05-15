package com.exam.jp.guidomia.framework.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.exam.jp.guidomia.core.data.Car

/**
 * Defines a car entity
 */
@Entity(tableName = "cars")
data class CarEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val customerPrice: Int,
    val make: String,
    val marketPrice: Int,
    val model: String,
    val prosList: List<String>,
    val consList: List<String>,
    val rating: Int
) {
    companion object {
        fun fromCar(car: Car) = CarEntity(
            car.id,
            car.customerPrice,
            car.make,
            car.marketPrice,
            car.model,
            car.prosList,
            car.consList,
            car.rating,
        )
    }

    fun toCar() = Car(
        id,
        customerPrice,
        make,
        marketPrice,
        model,
        prosList,
        consList,
        rating,
    )
}