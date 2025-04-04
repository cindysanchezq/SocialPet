package com.example.socialpet

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.socialpet.screens.ButtonScreen
import com.example.socialpet.screens.PhotosScreen
import com.example.socialpet.screens.ProfileScreen
import com.example.socialpet.screens.VideosScreen
import com.example.socialpet.screens.WebScreen


@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Web.route
    ) {
        composable(Screen.Profile.route) { ProfileScreen(navController) }
        composable(Screen.Button.route) { ButtonScreen(navController) }
        composable(Screen.Photos.route) { PhotosScreen(navController) }
        composable(Screen.Videos.route) { VideosScreen(navController) }
        composable(Screen.Web.route) { WebScreen(navController) }
    }
}