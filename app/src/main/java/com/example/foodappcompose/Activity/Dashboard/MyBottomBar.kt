package com.example.foodappcompose.Activity.Dashboard



import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodappcompose.R


@Composable
fun MyBottomBar(modifier: Modifier = Modifier) {
    val bottomMenuItemsList = prepareBottomMenu()
    val context = LocalContext.current

    var selectedItem by remember { mutableStateOf("Home") }

    BottomAppBar (
        backgroundColor = colorResource(R.color.white),
        elevation = 3.dp
    ){
        bottomMenuItemsList.forEach { bottomMenuItems ->
            BottomNavigationItem(
                selected = bottomMenuItems.label == selectedItem,
                onClick = {},
                icon = {
                    Icon(bottomMenuItems.icon,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 7.dp)
                            .size(20.dp))
                }
            )
        }
    }
}

data class BottomBarItems(
    val label: String,
    val icon: Painter
)

@Composable
fun prepareBottomMenu(): List<BottomBarItems> {
    val bottomMenuItemList = arrayListOf<BottomBarItems>()

    bottomMenuItemList.add(BottomBarItems(label = "Home", icon = painterResource(R.drawable.btn_1)))
    bottomMenuItemList.add(BottomBarItems(label = "Cart", icon = painterResource(R.drawable.btn_2)))
    bottomMenuItemList.add(
        BottomBarItems(
            label = "Favorite",
            icon = painterResource(R.drawable.btn_3)
        )
    )
    bottomMenuItemList.add(
        BottomBarItems(
            label = "Order",
            icon = painterResource(R.drawable.btn_4)
        )
    )
    bottomMenuItemList.add(
        BottomBarItems(
            label = "Profile",
            icon = painterResource(R.drawable.btn_5)
        )
    )

    return bottomMenuItemList
}