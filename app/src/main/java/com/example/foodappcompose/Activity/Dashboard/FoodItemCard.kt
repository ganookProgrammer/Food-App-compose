package com.example.foodappcompose.Activity.Dashboard

import android.R.attr.end
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import com.example.foodappcompose.Activity.DetailFood.DetailEachFoodActivity
import com.example.foodappcompose.Domain.FoodModel
import com.example.foodappcompose.R

@Composable
fun FoodItemCardGrid(item : FoodModel) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                colorResource(R.color.white), shape = RoundedCornerShape(14.dp))
            .clip(RoundedCornerShape(14.dp))
            .clickable{
                val intent = Intent(context, DetailEachFoodActivity::class.java).apply{
                    putExtra("object",item)
                }
                startActivity(context,intent,null)

            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = item.ImagePath,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
                .height(110.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )

        Text(
            text = item.Title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 5.dp, start = 8.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painterResource(R.drawable.star),
                contentDescription = null,
                modifier = Modifier.size(15.dp))
            Text(text = "${item.Star}",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(start = 2.dp))
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp, bottom = 2.dp, start = 8.dp,end = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

            Text(text = "$${item.Price}",
                color = colorResource(R.color.darkPurle),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold)

            Row(verticalAlignment = Alignment.CenterVertically){
                Image(
                    painterResource(R.drawable.time),
                    contentDescription = null,
                    modifier = Modifier.size(13.dp)
                )

                Text(text = "${item.TimeValue} min",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(start = 2.dp)
                    )

            }
        }
    }
}