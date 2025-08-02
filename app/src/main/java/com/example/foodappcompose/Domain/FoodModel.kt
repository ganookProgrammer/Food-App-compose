package com.example.foodappcompose.Domain

import android.os.Parcelable
import androidx.savedstate.serialization.serializers.ParcelableSerializer
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class FoodModel(
    var BestFood : Boolean = false,
    var CategoryId : String = "",
    var Description:String= "",
    var Id : Int = 0,
    var ImagePath : String = "",
    var LocationId : Int = 0,
    var Price : Int  = 0,
    var PriceId : Int = 0,
    var Star : Double = 0.0,
    var TimeId  :Int = 0,
    var TimeValue : Int = 0,
    var Title : String = "",
    var Calorie : Int = 0,
    var numberInCart : Int = 0
)
    : Parcelable