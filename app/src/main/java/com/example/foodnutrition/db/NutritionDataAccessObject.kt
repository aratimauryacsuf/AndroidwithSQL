package com.example.foodnutrition.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodnutrition.api.NutritionResponse


@Dao
interface NutritionDataAccessObject {

@Query("SELECT * FROM Nutrition")
fun getAllFood(): LiveData<List<NutritionResponse>>

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insertFood(food: List<NutritionResponse>)
}
