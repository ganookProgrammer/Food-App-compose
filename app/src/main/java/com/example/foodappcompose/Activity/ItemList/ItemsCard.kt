package com.example.foodappcompose.Activity.ItemList

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import com.example.foodappcompose.Activity.DetailFood.DetailEachFoodActivity
import com.example.foodappcompose.Domain.FoodModel
import com.example.foodappcompose.R

@Composable
fun ItemsList(items: List<FoodModel>) {

    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
        itemsIndexed(items) { index, items ->
            Items(items)
        }
    }
}
//
//@Preview
//@Composable
//private fun ItemListPreview() {
//    Items(item = previewFood)
//
//}

@Composable
fun Items(item: FoodModel) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .background(
                colorResource(R.color.white),
                shape = RoundedCornerShape(10.dp)
            )
            .wrapContentHeight()
            .clickable {
              val intent = Intent(context, DetailEachFoodActivity::class.java).apply{
                  putExtra("object",item)
              }
               startActivity(context,intent,null)
            }

    ) {
        FoodImage(item = item)
        FoodDetail(item = item)
    }
}

@Composable
fun RowScope.FoodDetail(item: FoodModel) {
    Column(
        modifier =
            Modifier.padding(start = 8.dp)
                .fillMaxWidth()
                .weight(1f)
    ) {
        Text(
            text = item.Title,
            color = colorResource(R.color.darkPurle),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 8.dp)
        )

        TextRow(item.TimeValue)
        RatingBarRow(item.Star)
        PriceRow(item.Price)
    }
}

@Composable
fun PriceRow(price: Int) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 8.dp)) {

        Text(
            text = "$price",
            color = colorResource(R.color.darkPurle),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = "+ Add",
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(8.dp)
                .background(
                    colorResource(R.color.green),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 8.dp)
        )
    }
}

@Composable
fun RatingBarRow(star: Double) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.star),
            contentDescription = null,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(text = "$star",
            style = MaterialTheme.typography.body1)
    }
}

@Composable
fun TextRow(textValue: Int) {
    Row(modifier = Modifier.padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(R.drawable.time),
            contentDescription = null,
            modifier = Modifier.padding(end = 8.dp))

        Text(
            text = "$textValue min",
            style = MaterialTheme.typography.body1
        )

    }
}

@Composable
fun FoodImage(item: FoodModel) {
    AsyncImage(
        model = item.ImagePath,
        contentDescription = null,
        modifier =
            Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    colorResource(R.color.white),
                    shape = RoundedCornerShape(10.dp)
                ),
        contentScale = ContentScale.Crop
    )
}