package com.example.foodappcompose.Activity.Dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodappcompose.R

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .padding(top = 48.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Image(painter = painterResource(R.drawable.profile),
            contentDescription = "Profile",
            modifier = Modifier.clickable{})

        var text by rememberSaveable { mutableStateOf("") }

        TextField(
            value = text , onValueChange = { text = it},
            label = {
                Text(text = "What   Would you like to eat?",
                    fontSize = 13.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray
                )
            },
            trailingIcon = {
                Image(
                    painter = painterResource(R.drawable.search),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            },
            shape = RoundedCornerShape(25.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = colorResource(R.color.white),
                unfocusedBorderColor = Color.Transparent,
                unfocusedLabelColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                textColor = Color.DarkGray
            ),
            modifier = Modifier.weight(1f)
                .padding(horizontal = 12.dp)
                .height(45.dp)
                .background(colorResource(R.color.grey), CircleShape)
        )
        Image(painterResource(R.drawable.bell_icon),
            contentDescription = null,
            modifier = Modifier.clickable{})
    }
}