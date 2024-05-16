package com.exam.jp.guidomia.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.exam.jp.guidomia.R

@Composable
fun Banner(modifier: Modifier = Modifier,
           painter: Painter,
           title: String, subtitle: String) {
    val height = 230.dp
    Box(
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .background(White, shape = shape),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )

        Column(modifier = Modifier.align(Alignment.BottomStart).padding(PaddingValues(start = 16.dp, bottom = 16.dp))) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = colorResource(id = R.color.white),
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = colorResource(id = R.color.white),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
            )
        }

    }
}