package com.exam.jp.guidomia.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.exam.jp.guidomia.R

// reference: https://alexzh.com/jetpack-compose-dropdownmenu/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenu(default: String, items: List<String>, onFilter: (filter: String) -> Unit) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    val finalList = listOf(default) + items
    var selectedText by remember { mutableStateOf(finalList[0]) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)),
        contentAlignment = Alignment.Center
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                finalList.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                            onFilter(item)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CarFilter(make: List<String>, model: List<String>, onFilterMake: (make: String) -> Unit, onFilterModel: (model: String) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clip(shape = RoundedCornerShape(8.dp, 8.dp, 8.dp, 8.dp))
        .background(colorResource(id = R.color.dark_gray))) {
        Text(
            text = "Filters",
            color = colorResource(id = R.color.white),
            style = MaterialTheme.typography.labelLarge,
            fontSize = 20.sp,
            modifier = Modifier.padding(PaddingValues(start = 24.dp, top = 16.dp))
        )
        ExposedDropdownMenu("Any Make", items = make) {
            onFilterMake(it)
        }
        ExposedDropdownMenu("Any Model", items = model) {
            onFilterModel(it)
        }
    }
}