package com.example.foodnutrition.db

import androidx.lifecycle.LiveData
import androidx.room.RoomDatabase

import com.example.foodnutrition.api.NutritionExecutor
import com.example.foodnutrition.api.NutritionResponse

class NutritionDatabaseRepository() {


    private val nutritionExecutor: NutritionExecutor
    private val nutritionDao: NutritionDatabase

    val nutritionLiveData: LiveData<List<NutritionResponse>> = nutritionDao.getAllFood()

    suspend fun fetchData(query: String) {
        val nutritionListEntries = NutritionExecutor().getNutrition(query.toString())
        nutritionDao.insertFood(nutritionListEntries)
    }
}



