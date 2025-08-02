package com.example.foodappcompose.Activity.Dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodappcompose.Domain.CategoryModel
import com.example.foodappcompose.Domain.FoodModel
import com.example.foodappcompose.R
import com.example.foodappcompose.ViewModel.MainViewModel
import com.example.foodappcompose.ui.theme.FoodAppComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val scaffoldState = rememberScaffoldState()

    val viewModel = MainViewModel()

    var showCategoryLoading by remember { mutableStateOf(true) }
    val categories = remember{ mutableStateListOf<CategoryModel>() }
    val bestFood = remember { mutableStateListOf<FoodModel>() }

    var showBestFoodLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.loadCategory().observeForever{
            categories.clear()
            categories.addAll(it)
            showCategoryLoading = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadBestFoods().observeForever {
            bestFood.clear()
            bestFood.addAll(it)
            showBestFoodLoading = false
        }
    }


    Scaffold(
        bottomBar = {MyBottomBar()},
        scaffoldState = scaffoldState
    ) {innerPadding ->

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.grey))
                .padding(innerPadding),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item(span = { GridItemSpan(2)}){
                TopBar()
            }

            item(span = {GridItemSpan(2)}){
                CategorySelection(categories,showCategoryLoading)
            }

            item(span = {GridItemSpan(2)}){
                Text(
                    text = "Foods for you",
                    color = colorResource(R.color.darkPurle),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            if (showBestFoodLoading){
                item(span = {GridItemSpan(2)}){
                    Box(contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                            .height(200.dp)){
                        CircularProgressIndicator()
                    }
                }
            }else{
                items(bestFood.size){
                    FoodItemCardGrid(item = bestFood[it])
                }
            }

    }

    }
    
}