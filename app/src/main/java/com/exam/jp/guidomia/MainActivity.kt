package com.exam.jp.guidomia

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.exam.jp.guidomia.core.data.Car
import com.exam.jp.guidomia.ui.cars.CarsScreen
import com.exam.jp.guidomia.ui.cars.CarsViewModel
import com.exam.jp.guidomia.ui.theme.GuidomiaTheme

class MainActivity : ComponentActivity() {

    private lateinit var carsViewModel: CarsViewModel

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        carsViewModel = ViewModelProvider(this)[CarsViewModel::class.java]
        carsViewModel.getAllCars()
        setContent {
            GuidomiaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val cars = remember { mutableStateListOf<Car>() }
                    val carsListState by carsViewModel.carsListState.collectAsStateWithLifecycle(this)
                    LaunchedEffect(key1 = carsListState.cars) {
                        cars.clear()
                        if (carsListState.cars.isNotEmpty()) {
                            cars.addAll(carsListState.cars)
                            // expand first item
                            cars.mapIndexed { index, car ->
                                car.id = index.toLong()
                            }
                            cars[0].expanded = true
                        }
                    }
                    CarsScreen(
                        innerPadding,
                        cars = cars
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GuidomiaTheme {
        Greeting("Android")
    }
}