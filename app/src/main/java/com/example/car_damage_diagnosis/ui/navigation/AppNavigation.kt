package com.example.car_damage_diagnosis.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.car_damage_diagnosis.ui.screens.history.HistoryScreen
import com.example.car_damage_diagnosis.ui.screens.home.HomeScreen
import com.example.car_damage_diagnosis.ui.screens.welcome.WelcomeScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Welcome
    ) {
        composable(Screen.Welcome) {
            WelcomeScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.History) { // Navigate to History
                        popUpTo(Screen.Welcome) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.History) {
            HistoryScreen()
        }

        composable(Screen.CameraCapture) {
            // TODO: Implement CameraCaptureScreen
        }
        
        composable(Screen.DamageAnalysis) {
            // TODO: Implement DamageAnalysisScreen
        }
        
        composable(Screen.Profile) {
            // TODO: Implement ProfileScreen
        }
        
        composable(Screen.Settings) {
            // TODO: Implement SettingsScreen
        }
    }
}
