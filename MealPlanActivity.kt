package com.example.foodsaver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.platform.LocalContext

class MealPlanActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bmi = intent?.getDoubleExtra("BMI_VALUE", 0.0) ?: 0.0

        setContent {
            // The theme should be applied here directly
            MaterialTheme {
                MealPlanScreen(bmi)
            }
        }
    }
}

@Composable
fun MealPlanScreen(bmi: Double) {
    var mealPlan by remember { mutableStateOf("") }

    // Generate meal plan based on BMI
    mealPlan = when {
        bmi < 18.5 -> "High-calorie meal plan:\n\n" +
                "  Breakfast: Avocado toast with eggs\n" +
                "  Ingredients: Avocado, whole grain bread, eggs, salt, pepper\n\n" +
                "  Lunch: Chicken pasta with a side of salad\n" +
                "  Ingredients: Chicken breast, pasta, mixed greens, tomatoes, cucumber, olive oil, balsamic vinegar\n\n" +
                "  Dinner: Grilled salmon with quinoa\n" +
                "  Ingredients: Salmon, quinoa, lemon, garlic, asparagus."
        bmi < 25 -> "Balanced meal plan:\n\n" +
                "  Breakfast: Oatmeal with fruits\n" +
                "  Ingredients: Oats, banana, berries, honey, cinnamon\n\n" +
                "  Lunch: Grilled chicken salad\n" +
                "  Ingredients: Chicken breast, lettuce, cherry tomatoes, cucumber, feta cheese, olive oil\n\n" +
                "  Dinner: Vegetable stir-fry with tofu\n" +
                "  Ingredients: Tofu, bell peppers, broccoli, soy sauce, ginger, garlic."
        bmi < 30 -> "Low-calorie meal plan:\n\n" +
                "  Breakfast: Greek yogurt with berries\n" +
                "  Ingredients: Greek yogurt, strawberries, blueberries, chia seeds\n\n" +
                "  Lunch: Quinoa salad with lean turkey\n" +
                "  Ingredients: Quinoa, turkey breast, cherry tomatoes, cucumber, parsley, lemon juice\n\n" +
                "  Dinner: Steamed fish with vegetables\n" +
                "  Ingredients: White fish fillet, carrots, green beans, lemon, dill."
        else -> "Weight loss meal plan:\n\n" +
                "  Breakfast: Smoothie with spinach and banana\n" +
                "  Ingredients: Spinach, banana, almond milk, chia seeds, peanut butter\n\n" +
                "  Lunch: Lentil soup with mixed greens\n" +
                "  Ingredients: Lentils, carrots, celery, onions, spinach, vegetable broth\n\n" +
                "  Dinner: Grilled chicken breast with steamed broccoli\n" +
                "  Ingredients: Chicken breast, broccoli, olive oil, garlic, lemon."
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Back button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = {
                (context as? ComponentActivity)?.onBackPressed()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF00C2C7) // Theme color
                )
            }
        }

        // Header
        Text(
            text = "Personalized Meal Planning",
            style = MaterialTheme.typography.titleLarge,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF00C2C7), // Theme color
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Meal plan card
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F8FF)), // Light background color for the card
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = mealPlan,
                    fontSize = 18.sp,
                    color = Color(0xFF333333),
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
