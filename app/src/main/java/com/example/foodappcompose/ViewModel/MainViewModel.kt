package com.example.foodappcompose.ViewModel

import androidx.lifecycle.LiveData
import com.example.foodappcompose.Domain.CategoryModel
import com.example.foodappcompose.Domain.FoodModel
import com.example.foodappcompose.Repository.MainRepository

class MainViewModel {
    private val repository = MainRepository()

    fun loadCategory() : LiveData<MutableList<CategoryModel>>{
        return repository.loadCategory()
    }

    fun loadBestFoods() : LiveData<MutableList<FoodModel>>{
        return repository.loadBestFood()
    }

    fun loadFiltered(id : String) : LiveData<MutableList<FoodModel>>{
        return repository.loadFilter(id)
    }
}