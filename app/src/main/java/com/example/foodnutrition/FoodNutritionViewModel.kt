package com.example.foodnutrition

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.util.Log
import kotlinx.coroutines.*
import androidx.lifecycle.MutableLiveData
import com.example.foodnutrition.api.*
import com.example.foodnutrition.db.Nutrition
import com.example.foodnutrition.db.NutritionDataAccessObject
import com.example.foodnutrition.db.NutritionDatabaseRepository


private const val TAG = "NutritionViewModel"

class FoodNutritionViewModel(private val repository: NutritionDatabaseRepository): ViewModel() {
    private var query: String = ""
//    private val nutritionDao: NutritionDataAccessObject


    // data from API
     var nutritionInformationLiveData: LiveData<List<NutritionResponse>> = repository.nutritionLiveData


    fun getQueryString(): String {
        return this.query
    }
fun setQueryText(query:String){
    this.query = query
}



fun getNutrition(query: String){
    viewModelScope.launch{
repository.fetchData(query)
    }
}

//    fun getNutrition(query:String):LiveData<List<NutritionResponse>>{
//        Log.d(TAG, "Received request to Nutrition # $query")
//this.nutritionInformationLiveData = NutritionExecutor().getNutrition(query.toString())
//
//        Log.d(TAG, "In viewModel ${this.nutritionInformationLiveData.value?.get(0)?.name}")
//        return this.nutritionInformationLiveData
//    }

}