package com.exam.jp.guidomia.ui.cars

import android.graphics.Typeface
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.exam.jp.guidomia.R
import com.exam.jp.guidomia.core.data.Car
import com.exam.jp.guidomia.ui.components.Banner
import com.exam.jp.guidomia.ui.components.CarFilter
import com.exam.jp.guidomia.ui.components.ExpandableCard
import com.exam.jp.guidomia.ui.theme.GuidomiaTheme

@RequiresApi(Build.VERSION_CODES.R)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarsScreen(paddingValues: PaddingValues, cars: List<Car>) {
    val carsCopy = arrayListOf(cars)
    val filterMake = remember { mutableStateOf("Any Make") }
    val filterModel = remember { mutableStateOf("Any Model") }

    fun filter() {

    }

    fun <T> SnapshotStateList<T>.swapList(newList: List<T>){
        clear()
        addAll(newList)
    }
    val carsList = remember { mutableStateListOf<Car>() }
    carsList.swapList(cars)

    Scaffold(
        topBar = {
            val context = LocalContext.current
            val fontFamily = remember {
                FontFamily(
                    typeface = Typeface.createFromAsset(context.assets, "dirtywar.otf")
                )
            }

            TopAppBar(
                colors = topAppBarColors(
                    containerColor = colorResource(id = R.color.orange),
                    titleContentColor = colorResource(id = R.color.white),
                ),
                title = {
                    Text(
                        text = "Guidomia",
                        color = colorResource(id = R.color.white),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontFamily = fontFamily),
                        fontSize = 28.sp
                    )
                }
            )
        },
        bottomBar = {

        },
        floatingActionButton = {

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {

            // Banner
            Banner(
                painter = painterResource(id = R.drawable.tacoma),
                title = "Tacoma 2021",
                subtitle = "Get yours now"
            )

            // Car Filter
            CarFilter(
                make = cars.map { it.make },
                model = cars.map { it.model },
                onFilterMake = {
                    filterMake.value = it
                    filter()
                }, onFilterModel = {
                    filterModel.value = it
                    filter()
                })

            // Cars List
            LazyColumn(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                itemsIndexed(carsList) { index, item ->
                    ExpandableCard(car = item, index) { positionClicked ->
                        // reset expanded values
                        cars.forEach { updatedCar ->
                            updatedCar.expanded = false
                        }
                        cars[positionClicked].expanded = true
                        carsList.swapList(cars)
                    }
                    if (index < cars.lastIndex)
                        Divider(
                            modifier = Modifier.padding(16.dp),
                            thickness = 4.dp,
                            color = colorResource(id = R.color.orange)
                        )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.R)
@Preview(showBackground = true)
@Composable
fun CarsScreenPreview() {
    GuidomiaTheme {
        CarsScreen(
            PaddingValues(),
            cars = arrayListOf(
                Car(
                    1,
                    125000,
                    "BMW",
                    125000,
                    "330i",
                    arrayListOf("4 wheel drive", "Disk brake", "Good sound system"),
                    arrayListOf("Bad direction"),
                    3,
                    false
                ),
                Car(
                    2,
                    125000,
                    "BMW",
                    125000,
                    "330i",
                    arrayListOf("4 wheel drive", "Disk brake", "Good sound system"),
                    arrayListOf("Bad direction"),
                    3,
                    false
                )
            )
        )
    }
}