package com.example.socialpet.screens

import android.content.Intent
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController

@Composable
fun ButtonScreen(navController: NavHostController) {
    val context = LocalContext.current // ✅ Obtiene el contexto de la aplicación

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp)) // Espacio superior
        Text(
            text = "Comparte los mejores momentos de tu mascota con la comunidad de SocialPet",
            fontSize = 18.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp)) // Espacio entre el texto y el botón
        Button(
            onClick = {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "¡Mira los mejores momentos de mi mascota en SocialPet!")
                    type = "text/plain"
                }
                context.startActivity(Intent.createChooser(shareIntent, "Compartir vía")) // ✅ Importanteee Usa el contexto correctamente
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Compartir")
        }
    }
}