package com.exam.jp.guidomia.framework

import com.exam.jp.guidomia.core.usecase.AddCar
import com.exam.jp.guidomia.core.usecase.DeleteCar
import com.exam.jp.guidomia.core.usecase.GetAllCars
import com.exam.jp.guidomia.core.usecase.GetCar

data class UseCases(
    val addCar: AddCar,
    val getAllCars: GetAllCars,
    val getCar: GetCar,
    val deleteCar: DeleteCar
)