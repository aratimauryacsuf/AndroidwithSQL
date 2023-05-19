package com.example.foodnutrition
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.foodnutrition.api.NutritionExecutor
import java.lang.Thread.sleep

private const val TAG ="queryFragment"
class FoodNutritionQueryFragment: Fragment() {
    private lateinit var foodNutritionViewModel: FoodNutritionViewModel
    private lateinit var getInfoButton: Button
    private lateinit var foodNutritionLabel: TextView
    private lateinit var foodQueryEditText: EditText




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.foodNutritionViewModel = ViewModelProvider(this.requireActivity())[FoodNutritionViewModel::class.java]

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_food_nutrition_query, container, false)
        this.foodNutritionLabel = view.findViewById(R.id.foodNutritionText)
        this.foodQueryEditText = view.findViewById(R.id.editText_food_query)
        this.getInfoButton = view.findViewById(R.id.informationButton)


        this.getInfoButton.setOnClickListener {
            val query =this.foodQueryEditText.text
            if(query.isNotEmpty()) {
                this.foodNutritionViewModel.getNutrition(query.toString())
                (activity as? MainActivity)?.refreshResponseFragment()
            }
            else{
                Toast.makeText(context, "Enter food or ingredient to get information", Toast.LENGTH_LONG).show()
            }
            Log.d(TAG,"Inside get info button click")
        }

        return view
    }

}