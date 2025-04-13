package com.example.foodsaver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                AppScreen()
            }
        }
    }
}

@Composable
fun AppScreen() {
    // 管理界面状态
    var isLoginScreen by remember { mutableStateOf(true) }

    if (isLoginScreen) {
        LoginScreen(onRegisterClick = { isLoginScreen = false })
    } else {
        RegisterScreen(onBackToLoginClick = { isLoginScreen = true })
    }
}

@Composable
fun LoginScreen(onRegisterClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 右上Register
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            TextButton(onClick = onRegisterClick) {
                Text(text = "Register", color = Color(0xFF000000))
            }
        }

        Spacer(modifier = Modifier.height(64.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {

            Image(
                painter = painterResource(id = R.drawable.asd),
                contentDescription = "App Logo",
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Welcome to our FoodSaver",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 25.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            InputField(label = "E-mail")
            Spacer(modifier = Modifier.height(8.dp))
            InputField(label = "Password", isPassword = true)

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { /* 登录逻辑 */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00C2C7)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Sign in", color = Color.White)
            }
        }
    }
}

@Composable
fun RegisterScreen(onBackToLoginClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 右上sign
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            TextButton(onClick = onBackToLoginClick) {
                Text(text = "Sign in", color = Color(0xFF000000))
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            InputField(label = "Household nickname")
            Spacer(modifier = Modifier.height(8.dp))
            InputField(label = "E-mail")
            Spacer(modifier = Modifier.height(8.dp))
            InputField(label = "Password", isPassword = true)
            Spacer(modifier = Modifier.height(8.dp))
            InputField(label = "Repeat password", isPassword = true)

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { /* 注册逻辑 */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00C2C7)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Register", color = Color.White)
            }
        }
    }
}

@Composable
fun InputField(label: String, isPassword: Boolean = false) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(label) },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = if (isPassword) ImeAction.Done else ImeAction.Next
        ),
        modifier = Modifier.fillMaxWidth()
    )
}
