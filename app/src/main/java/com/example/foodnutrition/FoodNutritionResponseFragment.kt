package com.example.foodnutrition

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.foodnutrition.api.NutritionExecutor
import com.example.foodnutrition.api.NutritionResponse
import com.example.foodnutrition.db.NutritionDatabase
import com.example.foodnutrition.db.NutritionDatabaseRepository

private const val TAG = "ResponseFragment"

class FoodNutritionResponseFragment : Fragment() {
    private lateinit var foodNutritionViewModel: FoodNutritionViewModel
    private lateinit var nutritionRecyclerView: RecyclerView
    private var adapter: NutritionAdapter? = null

    private lateinit var nameValue: TextView
    private lateinit var caloriesLabel: TextView
    private lateinit var caloriesValue: TextView

    private lateinit var totalFatLabel: TextView
    private lateinit var totalFatValue: TextView

    private lateinit var proteinLabel: TextView
    private lateinit var proteinValue: TextView

    private lateinit var carbohydratesLabel: TextView
    private lateinit var carbohydratesValue: TextView

    //private lateinit var nutritionDao:
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        this.foodNutritionViewModel =
//            ViewModelProvider(this.requireActivity())[FoodNutritionViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_food_nutrition_response, container, false)
        this.nutritionRecyclerView = view.findViewById(R.id.nutrition_recycler_view) as RecyclerView
        this.nutritionRecyclerView.layoutManager = LinearLayoutManager(context)

//      val nutritionDatabase = Room.databaseBuilder(
//            requireContext(),
//             NutritionDatabase::class.java,
//            "nutrition_database"
//        )
//        val nutritionRepository = NutritionDatabaseRepository(
//NutritionExecutor(),
//
//        )

        this.foodNutritionViewModel =
            ViewModelProvider(this, FoodNutritionViewModelFactory())[FoodNutritionViewModel::class.java]

        return view

    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//    }
    private fun updateUI() {

        this.foodNutritionViewModel.nutritionInformationLiveData.observe(
            this.viewLifecycleOwner
        ) { nutritionTemplate ->
            adapter = NutritionAdapter(nutritionTemplate)
            this.nutritionRecyclerView.adapter = adapter
        }

    }

    private inner class NutritionHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var nutrition: NutritionResponse

        private val name: TextView = this.itemView.findViewById(R.id.name_value)
        private val calories_label: TextView = this.itemView.findViewById(R.id.calories_label)
        private val calories_value: TextView = this.itemView.findViewById(R.id.calories_value)
        private val totalFat_label: TextView = this.itemView.findViewById(R.id.total_fat_label)
        private val totalFat_value: TextView = this.itemView.findViewById(R.id.total_fat_value)
        private val protein_label: TextView = this.itemView.findViewById(R.id.protein_label)
        private val protein_value: TextView = this.itemView.findViewById(R.id.protein_value)
        private val carb_label: TextView = this.itemView.findViewById(R.id.carbohydrates_label)
        private val carb_value: TextView = this.itemView.findViewById(R.id.carbohydrates_value)

        @SuppressLint("ResourceType")
        fun bind(nutrition: NutritionResponse) {
            this.nutrition = nutrition
            this.name.text = this.nutrition.name.toString()
            this.calories_label.text = "Calories"
            this.calories_value.text = this.nutrition.calories.toString()
            this.totalFat_label.text = "Total Fat"
            this.totalFat_value.text = this.nutrition.totalFat.toString()
            this.protein_label.text = "Protein"
            this.protein_value.text = this.nutrition.protein.toString()
            this.carb_label.text = "Carbohydrates"
            this.carb_value.text = this.nutrition.totalFat.toString()

        }
    }

    private inner class NutritionAdapter(var nutritionList: List<NutritionResponse>) :
        RecyclerView.Adapter<NutritionHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutritionHolder {
            val view = layoutInflater.inflate(R.layout.list_item_foodnutrition, parent, false)
            return NutritionHolder(view)
        }

        override fun onBindViewHolder(holder: NutritionHolder, position: Int) {
            val nutrition = this.nutritionList.get(position)
            if (nutrition != null) {
                holder.bind(nutrition)
            }
        }

        override fun getItemCount(): Int = nutritionList.size
    }

    fun refresh() {
        Log.d(TAG, "In refresh of response fragment")


        updateUI()

    }
}