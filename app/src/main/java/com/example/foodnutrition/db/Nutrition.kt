package com.example.foodnutrition.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Nutrition")
data class Nutrition (
@PrimaryKey(autoGenerate = true) val id : Int,
val name: String,
val  calories: Double,
val totalFat:Double,
val protein: Double,
val carbs: Double
        )
