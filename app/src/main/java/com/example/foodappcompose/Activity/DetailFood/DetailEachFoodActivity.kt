package com.example.foodappcompose.Activity.DetailFood

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.foodappcompose.Domain.FoodModel
import com.example.foodappcompose.Helper.previewFood
import com.example.foodappcompose.ManagmentCart
import com.example.foodappcompose.R

class DetailEachFoodActivity : AppCompatActivity() {
    private lateinit var item: FoodModel
    private lateinit var managmentCart: ManagmentCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        item = intent.getParcelableExtra<FoodModel>("object") as FoodModel
        item.numberInCart = 1
        managmentCart = ManagmentCart(this)

        setContent {
            DetailScreen(
                item = item,
                onBackClick = { finish() },
                onAddToCartClick = {
                    managmentCart.insertItem(item)
                }
            )
        }
    }
}

@Composable
fun DetailScreenPreview(modifier: Modifier = Modifier) {
    DetailScreen(
        item = previewFood,
        onBackClick = {},
        onAddToCartClick = {}
    )

}

@Composable
fun DetailScreen(item: FoodModel, onBackClick: () -> Unit, onAddToCartClick: () -> Unit) {
    var numberInCart by remember {
        mutableStateOf(item.numberInCart)
    }

    ConstraintLayout {
        val (footer, column) = createRefs()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.grey))
                .verticalScroll(rememberScrollState())
                .constrainAs(column) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
                .padding(bottom = 100.dp)
        ) {

            HeaderSection(item = item, onBackClick = onBackClick)

            TitleNumberRow(
                item = item,
                numberInCart = numberInCart,
                onIncrement = {
                    numberInCart++
                    item.numberInCart = numberInCart
                },
                onDecrement = {
                    if (numberInCart >1){
                        numberInCart--
                        item.numberInCart = numberInCart
                    }
                }
            )

            Text(
                text = "$${item.Price}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.black),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )

            RowDetail(item)
            DescriptionSection(item.Description)
            RecommendedList()
        }
        FooterSection(
            onAddToCartClick,
            totalPrice = ((item.Price*numberInCart).toDouble()),
            Modifier.constrainAs(footer) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            }
        )
    }
}