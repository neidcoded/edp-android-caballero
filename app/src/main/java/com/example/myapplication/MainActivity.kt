package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

import androidx.compose.ui.graphics.Color

// Liceo de Cagayan University Colors
val LiceoMaroon = Color(0xFF800000)
val LiceoGold = Color(0xFFFFD700)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            MaterialTheme {

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = Color.White
                    ) {

                        val navController = rememberNavController()

                        NavHost(
                            navController = navController,
                            startDestination = Home
                        ) {

                            composable<Home> {

                                HomeScreen(
                                    onShowGreeting = { typedName ->

                                        navController.navigate(
                                            Greeting(
                                                userName = typedName
                                            )
                                        )
                                    }
                                )
                            }

                            composable<Greeting> { backStackEntry ->

                                val greeting: Greeting =
                                    backStackEntry.toRoute()

                                GreetingScreen(
                                    userName = greeting.userName
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
