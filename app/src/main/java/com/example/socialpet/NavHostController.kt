package com.example.socialpet

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.socialpet.screens.ButtonScreen
import com.example.socialpet.screens.PhotosScreen
import com.example.socialpet.screens.ProfileScreen
import com.example.socialpet.screens.VideosScreen
import com.example.socialpet.screens.WebScreen


@Composable

fun NavHostController(){
    val navController= rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "profile"
        ){
        composable(route="profile") { ProfileScreen(navController) }
        composable(route ="button" ) { ButtonScreen(navController) }
        composable(route="photos") { PhotosScreen(navController) }
        composable(route="videos") { VideosScreen(navController) }
        composable(route="web") { WebScreen(navController) }

    }







}
