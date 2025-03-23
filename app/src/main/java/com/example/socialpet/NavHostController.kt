package com.example.socialpet

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.socialpet.screens.BotonesScreen
import com.example.socialpet.screens.FotosScreen
import com.example.socialpet.screens.PerfilScreen
import com.example.socialpet.screens.VideosScreen
import com.example.socialpet.screens.WebScreen


@Composable

fun NavHostController(){
    val navController= rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "perfil"
        ){
        composable(route="perfil") { PerfilScreen(navController) }
        composable(route ="botones" ) { BotonesScreen(navController) }
        composable(route="fotos") { FotosScreen(navController) }
        composable(route="video") { VideosScreen(navController) }
        composable(route="web") { WebScreen(navController) }

    }







}
