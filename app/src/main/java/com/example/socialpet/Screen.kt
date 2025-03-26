package com.example.socialpet

sealed class Screen(val route: String, val title: String){
    object Profile : Screen("profile", "Perfil")
    object Button : Screen("button", "Botones")
    object Photos : Screen("photos", "Fotos")
    object Videos : Screen("videos", "Video")
    object Web : Screen("web", "Web")
}