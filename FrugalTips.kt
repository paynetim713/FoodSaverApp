package com.example.foodsaver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.foodsaver.ui.theme.cardBackgroundColor
import com.example.foodsaver.ui.theme.descriptionTextColor
import com.example.foodsaver.ui.theme.secondaryColor
import com.example.foodsaver.ui.theme.textColor

data class Category(val name: String, val items: List<FoodItem>)
data class FoodItem(val name: String, val description: String, val imageResId: Int)

class FrugalTipsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = lightColorScheme()) {
                FoodNavHost()
            }
        }
    }
}

@Composable
fun FoodNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "category_screen") {
        composable("category_screen") {
            CategoryScreen(navController = navController)
        }
        composable("food_screen/{categoryName}") { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName") ?: "Vegetables"
            FoodScreen(categoryName = categoryName, navController = navController)
        }
    }
}

@Composable
fun CategoryScreen(navController: NavController) {
    val categories = listOf(
        Category("Vegetables", listOf(
            FoodItem("Carrot", "Store carrots in a sealed container in the refrigerator.", R.drawable.carrot),
            FoodItem("Broccoli", "Store broccoli in the fridge and consume within a few days.", R.drawable.broccoli),
            FoodItem("Cabbage", "Store cabbage in the refrigerator in a plastic bag.", R.drawable.cabbage),
            FoodItem("Spinach", "Keep spinach in the fridge and consume quickly.", R.drawable.spinach),
            FoodItem("Potato", "Store potatoes in a cool, dark place away from sunlight.", R.drawable.potato),
            FoodItem("Tomato", "Store tomatoes at room temperature and avoid refrigeration.", R.drawable.tomato),
            FoodItem("Cucumber", "Refrigerate cucumbers to keep them fresh for longer.", R.drawable.cucumber)
        )),
        Category("Meat", listOf(
            FoodItem("Chicken", "Store chicken in the refrigerator or freezer, and consume it within 1-2 days of purchase.", R.drawable.chicken),
            FoodItem("Beef", "Store beef in the refrigerator for up to 5 days, or freeze for longer storage.", R.drawable.beef),
            FoodItem("Pork", "Keep pork refrigerated and use within 2-3 days.", R.drawable.pork),
            FoodItem("Lamb", "Store lamb in the fridge and use it within 2-3 days.", R.drawable.lamb),
            FoodItem("Fish", "Store fish in the fridge for up to 2 days, or freeze it for longer storage.", R.drawable.fish),
            FoodItem("Turkey", "Store turkey in the refrigerator and consume within 1-2 days.", R.drawable.turkey),
            FoodItem("Bacon", "Refrigerate bacon and consume within a few days.", R.drawable.bacon)
        )),
        Category("Seafood", listOf(
            FoodItem("Salmon", "Store salmon in the fridge for up to 2 days, or freeze it for longer storage.", R.drawable.salmon),
            FoodItem("Tuna", "Store tuna in the fridge or freeze it.", R.drawable.tuna),
            FoodItem("Shrimp", "Keep shrimp refrigerated or frozen until use.", R.drawable.shrimp),
            FoodItem("Crab", "Store crab in the fridge and consume it within 1-2 days.", R.drawable.crab),
            FoodItem("Lobster", "Refrigerate lobster and consume it quickly after purchase.", R.drawable.lobster),
            FoodItem("Mussels", "Store mussels in the fridge and consume within 1-2 days.", R.drawable.mussels),
            FoodItem("Clams", "Store clams in the fridge and use within 1-2 days.", R.drawable.clams)
        )),
        Category("Fruits", listOf(
            FoodItem("Apple", "Store apples in the fridge or a cool place.", R.drawable.apple),
            FoodItem("Banana", "Bananas should be stored at room temperature.", R.drawable.banana),
            FoodItem("Orange", "Store oranges at room temperature or refrigerate.", R.drawable.orange),
            FoodItem("Grapes", "Refrigerate grapes to maintain freshness.", R.drawable.grapes),
            FoodItem("Strawberry", "Store strawberries in the fridge.", R.drawable.strawberry),
            FoodItem("Blueberry", "Refrigerate blueberries and consume quickly.", R.drawable.blueberry),
            FoodItem("Pineapple", "Store pineapple in the fridge or at room temperature.", R.drawable.pineapple)
        )),
        Category("Fast Food", listOf(
            FoodItem("Pizza", "Store pizza in the fridge and consume within 2-3 days.", R.drawable.pizza),
            FoodItem("Burger", "Burgers should be kept refrigerated and eaten within a day.", R.drawable.burger),
            FoodItem("Fries", "Store fries in the fridge and consume within a day.", R.drawable.fries),
            FoodItem("Hotdog", "Store hotdogs in the fridge and use within a few days.", R.drawable.hotdog),
            FoodItem("Chicken Nuggets", "Keep chicken nuggets refrigerated.", R.drawable.chickennuggets),
            FoodItem("Sandwich", "Store sandwiches in the fridge and consume within a day.", R.drawable.sandwich),
            FoodItem("Pasta", "Store pasta in the fridge and use within a few days.", R.drawable.pasta)
        )),
        Category("Tofu & Plant-Based", listOf(
            FoodItem("Tofu", "Store tofu in water in the fridge and change the water daily.", R.drawable.tofu),
            FoodItem("Tempeh", "Keep tempeh refrigerated and use within a week.", R.drawable.tempeh),
            FoodItem("Edamame", "Store edamame in the fridge or freezer.", R.drawable.edamame),
            FoodItem("Lentils", "Store lentils in a cool, dry place.", R.drawable.lentils),
            FoodItem("Chickpeas", "Store chickpeas in an airtight container.", R.drawable.chickpeas),
            FoodItem("Black Beans", "Store black beans in a cool, dry place.", R.drawable.blackbeans),
            FoodItem("Soy Milk", "Refrigerate soy milk after opening.", R.drawable.soymilk)
        ))
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Frugal Tips",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp),
                color = textColor
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Choose a category to explore food items!",
                fontSize = 18.sp,
                color = textColor
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 分类按钮
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .shadow(4.dp, RoundedCornerShape(12.dp))
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF9F9F9))
            ) {
                items(categories) { category ->
                    CategoryButton(
                        text = category.name,
                        onClick = {
                            navController.navigate("food_screen/${category.name}")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = secondaryColor),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            color = textColor,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun FoodScreen(categoryName: String, navController: NavController) {
    val category = getCategoryByName(categoryName)
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // 返回按钮
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color(0xFF00C2C7)
            )
        }

        Text(
            text = category.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn {
            items(category.items) { item ->
                FoodItemButton(item = item, onClick = {
                })
            }
        }
    }
}
@Composable
fun FoodItemButton(item: FoodItem, onClick: () -> Unit) {
    var isDescriptionVisible by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                isDescriptionVisible = !isDescriptionVisible
                onClick()
            }
            .background(cardBackgroundColor),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = item.name,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = textColor
                )
                if (isDescriptionVisible) {
                    Text(
                        text = item.description,
                        fontSize = 14.sp,
                        color = descriptionTextColor
                    )
                }
            }
        }
    }
}



// 明确指定返回值类型为 Category
fun getCategoryByName(name: String): Category {
    val categories = listOf(
        Category("Vegetables", listOf(
            FoodItem("Carrot", "Store carrots in a sealed container in the refrigerator.", R.drawable.carrot),
            FoodItem("Broccoli", "Store broccoli in the fridge and consume within a few days.", R.drawable.broccoli),
            FoodItem("Cabbage", "Store cabbage in the refrigerator in a plastic bag.", R.drawable.cabbage),
            FoodItem("Spinach", "Keep spinach in the fridge and consume quickly.", R.drawable.spinach),
            FoodItem("Potato", "Store potatoes in a cool, dark place away from sunlight.", R.drawable.potato),
            FoodItem("Tomato", "Store tomatoes at room temperature and avoid refrigeration.", R.drawable.tomato),
            FoodItem("Cucumber", "Refrigerate cucumbers to keep them fresh for longer.", R.drawable.cucumber)
        )),
        Category("Meat", listOf(
            FoodItem("Chicken", "Store chicken in the refrigerator or freezer, and consume it within 1-2 days of purchase.", R.drawable.chicken),
            FoodItem("Beef", "Store beef in the refrigerator for up to 5 days, or freeze for longer storage.", R.drawable.beef),
            FoodItem("Pork", "Keep pork refrigerated and use within 2-3 days.", R.drawable.pork),
            FoodItem("Lamb", "Store lamb in the fridge and use it within 2-3 days.", R.drawable.lamb),
            FoodItem("Fish", "Store fish in the fridge for up to 2 days, or freeze it for longer storage.", R.drawable.fish),
            FoodItem("Turkey", "Store turkey in the refrigerator and consume within 1-2 days.", R.drawable.turkey),
            FoodItem("Bacon", "Refrigerate bacon and consume within a few days.", R.drawable.bacon)
        )),
        Category("Seafood", listOf(
            FoodItem("Salmon", "Store salmon in the fridge for up to 2 days, or freeze it for longer storage.", R.drawable.salmon),
            FoodItem("Tuna", "Store tuna in the fridge or freeze it.", R.drawable.tuna),
            FoodItem("Shrimp", "Keep shrimp refrigerated or frozen until use.", R.drawable.shrimp),
            FoodItem("Crab", "Store crab in the fridge and consume it within 1-2 days.", R.drawable.crab),
            FoodItem("Lobster", "Refrigerate lobster and consume it quickly after purchase.", R.drawable.lobster),
            FoodItem("Mussels", "Store mussels in the fridge and consume within 1-2 days.", R.drawable.mussels),
            FoodItem("Clams", "Store clams in the fridge and use within 1-2 days.", R.drawable.clams)
        )),
        Category("Fruits", listOf(
            FoodItem("Apple", "Store apples in the fridge or a cool place.", R.drawable.apple),
            FoodItem("Banana", "Bananas should be stored at room temperature.", R.drawable.banana),
            FoodItem("Orange", "Store oranges at room temperature or refrigerate.", R.drawable.orange),
            FoodItem("Grapes", "Refrigerate grapes to maintain freshness.", R.drawable.grapes),
            FoodItem("Strawberry", "Store strawberries in the fridge.", R.drawable.strawberry),
            FoodItem("Blueberry", "Refrigerate blueberries and consume quickly.", R.drawable.blueberry),
            FoodItem("Pineapple", "Store pineapple in the fridge or at room temperature.", R.drawable.pineapple)
        )),
        Category("Fast Food", listOf(
            FoodItem("Pizza", "Store pizza in the fridge and consume within 2-3 days.", R.drawable.pizza),
            FoodItem("Burger", "Burgers should be kept refrigerated and eaten within a day.", R.drawable.burger),
            FoodItem("Fries", "Store fries in the fridge and consume within a day.", R.drawable.fries),
            FoodItem("Hotdog", "Store hotdogs in the fridge and use within a few days.", R.drawable.hotdog),
            FoodItem("Chicken Nuggets", "Keep chicken nuggets refrigerated.", R.drawable.chickennuggets),
            FoodItem("Sandwich", "Store sandwiches in the fridge and consume within a day.", R.drawable.sandwich),
            FoodItem("Pasta", "Store pasta in the fridge and use within a few days.", R.drawable.pasta)
        )),
        Category("Tofu & Plant-Based", listOf(
            FoodItem("Tofu", "Store tofu in water in the fridge and change the water daily.", R.drawable.tofu),
            FoodItem("Tempeh", "Keep tempeh refrigerated and use within a week.", R.drawable.tempeh),
            FoodItem("Edamame", "Store edamame in the fridge or freezer.", R.drawable.edamame),
            FoodItem("Lentils", "Store lentils in a cool, dry place.", R.drawable.lentils),
            FoodItem("Chickpeas", "Store chickpeas in an airtight container.", R.drawable.chickpeas),
            FoodItem("Black Beans", "Store black beans in a cool, dry place.", R.drawable.blackbeans),
            FoodItem("Soy Milk", "Refrigerate soy milk after opening.", R.drawable.soymilk)
        ))
    )

    return categories.first { it.name == name }
}