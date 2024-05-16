package com.exam.jp.guidomia.ui.cars

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import com.exam.jp.guidomia.core.data.Car
import com.exam.jp.guidomia.framework.UseCases
import com.exam.jp.guidomia.framework.di.ApplicationModule
import com.exam.jp.guidomia.framework.di.DaggerViewModelComponent
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

data class CarsListState(
    var cars: List<Car> = emptyList(),
)

class CarsViewModel(private val application: Application) : AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var useCases: UseCases

    @Inject
    lateinit var sharedPreference: SharedPreferences

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(getApplication()))
            .build()
            .inject(this)
    }

    private val _carsListState = MutableStateFlow(CarsListState())
    val carsListState: StateFlow<CarsListState> = _carsListState
    fun getAllCars() {
        coroutineScope.launch {
            val isFirstTime = sharedPreference.getBoolean("isFirstTime", true)
            if (isFirstTime) {
                // if app is opened the first time, load data from local json
                val carsList = getJsonDataFromAsset("car_list.json")
                if (carsList != null) {
                    val jsonCars = jsonToCar(carsList)
                    _carsListState.value = CarsListState(cars = jsonCars)
                    jsonCars.forEach { car ->
                        useCases.addCar(car)
                    }
                    sharedPreference.edit().putBoolean("isFirstTime", false).apply()
                }
            } else {
                // cars already added in database, use this data instead
                val carsList = useCases.getAllCars()
                _carsListState.value = CarsListState(cars = carsList)
            }
        }
    }

    private fun getJsonDataFromAsset(fileName: String): String? {
        val jsonString: String
        try {
            jsonString = application.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    private fun jsonToCar(jsonString: String): List<Car> {
        val listPersonType = object : TypeToken<List<Car>>() {}.type
        return Gson().fromJson(jsonString, listPersonType)
    }
}