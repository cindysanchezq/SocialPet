package com.example.socialpet.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun WebScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "En esta sección encontrarás enlaces a sitios de interés para el cuidado de tu mascota. Desde blogs con consejos sobre salud y entrenamiento hasta tiendas con los mejores productos para consentir a tu amigo peludo.",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Tienda - Laika",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
                navController.navigate("webview_screen/https://laika.com.co/")
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Cuidado y salud",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
                navController.navigate("webview_screen/https://www.desparasitatumascota.es/blog")
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Entrenamiento",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
                navController.navigate("webview_screen/https://www.zooplus.es/magazine/perros/adiestramiento-canino/adiestrador-de-perros")
            }
        )
    }
}