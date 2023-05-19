package com.example.foodnutrition.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodnutrition.api.NutritionResponse

@Database(entities = [NutritionResponse::class], version = 1)
abstract class NutritionDatabase : RoomDatabase() {
    abstract fun nutritionDao(): NutritionDataAccessObject
}