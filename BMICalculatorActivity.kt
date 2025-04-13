package com.example.foodsaver

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class BMICalculatorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Directly apply the theme color here without MyAppTheme
            MaterialTheme(
                colorScheme = lightColorScheme(
                    primary = Color(0xFF00C2C7),  // Theme color
                    onPrimary = Color.White
                )
            ) {
                BMICalculatorScreen()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun BMICalculatorScreen() {
        val focusManager = LocalFocusManager.current
        var height by remember { mutableStateOf("") }
        var weight by remember { mutableStateOf("") }
        var bmiResult by remember { mutableStateOf<Double?>(null) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF0F8FF)) // Simple light background color
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { focusManager.clearFocus() })
                }
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "Your Weight Partner",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF00C2C7), // Theme color
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Height input
            OutlinedTextField(
                value = height,
                onValueChange = { height = it },
                label = { Text("Height (m)", color = Color.Gray) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF00C2C7), // Focused border in theme color
                    unfocusedBorderColor = Color.Gray
                ),
                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Weight input
            OutlinedTextField(
                value = weight,
                onValueChange = { weight = it },
                label = { Text("Weight (kg)", color = Color.Gray) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF00C2C7),
                    unfocusedBorderColor = Color.Gray
                ),
                textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Calculate button
            Button(
                onClick = {
                    val heightValue = height.toDoubleOrNull()
                    val weightValue = weight.toDoubleOrNull()
                    if (heightValue != null && weightValue != null && heightValue > 0) {
                        bmiResult = weightValue / (heightValue * heightValue)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00C2C7)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calculate BMI", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Show BMI result
            bmiResult?.let { bmi ->
                Text(
                    text = "Your BMI: %.2f".format(bmi),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF00C2C7),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val intent = Intent(this@BMICalculatorActivity, MealPlanActivity::class.java)
                        intent.putExtra("BMI_VALUE", bmi)
                        startActivity(intent)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00C2C7)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Check Your Meal Plan", color = Color.White)
                }
            }
        }
    }
}
