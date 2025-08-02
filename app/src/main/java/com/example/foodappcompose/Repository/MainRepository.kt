package com.example.foodappcompose.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foodappcompose.Domain.CategoryModel
import com.example.foodappcompose.Domain.FoodModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

class MainRepository {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun  loadCategory() : LiveData<MutableList<CategoryModel>>{
        val listData = MutableLiveData<MutableList<CategoryModel>>()

        val ref = firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<CategoryModel>()
                for (childSnapshot in snapshot.children){
                    val item = childSnapshot.getValue(CategoryModel::class.java)
                    item?.let {list.add(it)}
                }
                listData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
        return listData
    }

    fun loadBestFood() : LiveData<MutableList<FoodModel>>{
        val listData = MutableLiveData<MutableList<FoodModel>>()
        val ref = firebaseDatabase.getReference("Foods")
        val query : Query = ref.orderByChild("BestFood").equalTo(true)
        query.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<FoodModel>()
                for (childerSnapshot in snapshot.children){
                    val list = childerSnapshot.getValue(FoodModel::class.java)
                    if (list !=null){
                        lists.add(list)
                    }
                }
                listData.value =lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return listData
    }

    fun loadFilter(id :String): LiveData<MutableList<FoodModel>>{
        val ref = firebaseDatabase.getReference("Foods")
        val listData = MutableLiveData<MutableList<FoodModel>>()
        val query : Query = ref.orderByChild("CategoryId").equalTo(id)
        query.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               val lists = mutableListOf<FoodModel>()
                for (childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(FoodModel::class.java)
                    if(list !=null){
                        lists.add(list)
                    }
                }
                listData.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        return listData

    }
}