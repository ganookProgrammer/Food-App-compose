package com.example.foodappcompose.Activity.DetailFood

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.example.foodappcompose.Domain.FoodModel
import com.example.foodappcompose.Helper.previewFood
import com.example.foodappcompose.R
import java.nio.file.WatchEvent

@Preview
@Composable
 fun HeaderSection(
    item: FoodModel = previewFood,
    onBackClick: () -> Unit = {}
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        val (back, fav, mainImage, arc) = createRefs()
        Image(
            painter = rememberAsyncImagePainter(model = item.ImagePath),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
                .constrainAs(mainImage) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
        )

        BackButton(onBackClick, Modifier.constrainAs(back) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        })

        FavoriteButton(onClick = {}, Modifier.constrainAs(fav) {
            top.linkTo(parent.top)
            end.linkTo(parent.end )
        })

        Image(
            painter = painterResource(R.drawable.arc),
            contentDescription = null,
            modifier = Modifier.constrainAs(arc) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end )
            }
        )
    }
}

@Composable
fun BackButton(onClick: () -> Unit,modifier: Modifier = Modifier) {
    Image(
        painterResource(R.drawable.back),
        contentDescription = null,
        modifier = modifier
            .padding(start = 16.dp, top = 48.dp)
            .clickable { onClick() })
}
@Composable
fun FavoriteButton(onClick: () -> Unit,modifier: Modifier = Modifier) {
    Image(
        painterResource(R.drawable.fav_icon),
        contentDescription = null,
        modifier = modifier
            .padding(end = 16.dp, top = 48.dp)
            .clickable { onClick() })
}