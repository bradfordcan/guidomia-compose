package com.exam.jp.guidomia.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.exam.jp.guidomia.R
import com.exam.jp.guidomia.core.data.Car
import com.exam.jp.guidomia.ui.theme.GuidomiaTheme
import com.exam.jp.guidomia.util.HexToJetpackColor
import com.exam.jp.guidomia.util.shortenPrice

@RequiresApi(Build.VERSION_CODES.R)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableCard(car: Car) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.light_gray),
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.light_gray)),
        onClick = {
            expanded = !expanded
        }
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .semantics(mergeDescendants = true) {},
            verticalAlignment = Alignment.CenterVertically
        ) {
            CarImage(1)
            Spacer(Modifier.width(8.dp))
            Column {
                Text(
                    text = "${car.make} ${car.model}",
                    color = colorResource(id = R.color.dark_gray),
                    style = MaterialTheme.typography.labelLarge,
                    fontSize = 20.sp,
                )

                Text(
                    text = "Price: ${shortenPrice(car.marketPrice.toLong())}",
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(id = R.color.dark_gray),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                )

                RatingBar(rating = car.rating)
            }
        }
        if (expanded) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Pros:",
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.dark_gray),
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodySmall
                )
                BulletItems(car.prosList)
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Cons:",
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.dark_gray),
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodySmall
                )
                BulletItems(car.consList)
            }
        }
    }
}

@Composable
fun BulletItems(items: List<String>) {
    val finalList = arrayListOf<String>()
    finalList.addAll(items)

    if(items.none { it.isNotEmpty() }) {
        finalList.add("None")
    }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        finalList.filter { it.isNotEmpty() }.forEach {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val isNone = it.contentEquals("None")
                if(isNone) {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 8.dp)
                            .size(8.dp)
                            .background(colorResource(id = android.R.color.transparent), shape = CircleShape),
                    )
                    Text(
                        text = it,
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Italic,
                        color = colorResource(id = R.color.dark_gray),
                        style = MaterialTheme.typography.bodySmall
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 8.dp)
                            .size(8.dp)
                            .background(colorResource(id = R.color.orange), shape = CircleShape),
                    )
                    Text(
                        text = it,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

            }
        }
    }
}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int = 0,
    stars: Int = 5,
    starsColor: Color = HexToJetpackColor.getColor("FC6016"),
) {
    Row(modifier = modifier) {
        repeat(rating) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = starsColor,
                modifier = Modifier.size(18.dp)
            )
        }
        repeat(stars - rating) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}


@Composable
private fun CarImage(id: Long) {
    val imageModifier = Modifier
        .width(140.dp)
        .height(70.dp)
        .fillMaxWidth()
        .clip(shape = MaterialTheme.shapes.medium)
    Image(
        painter = painterResource(
            LocalContext.current.resources.getIdentifier(
                "image${id}",
                "drawable",
                LocalContext.current.packageName
            )
        ),
        contentDescription = null, // decorative
        modifier = imageModifier,
        contentScale = ContentScale.FillHeight
    )
}

@RequiresApi(Build.VERSION_CODES.R)
@Preview(showBackground = true)
@Composable
fun CardPreview() {
    GuidomiaTheme {
        // CarImage(1)
        ExpandableCard(
            car = Car(
                1,
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
    }
}