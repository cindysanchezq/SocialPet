package com.example.socialpet.screens

import android.annotation.SuppressLint
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebScreen(navController: NavHostController) {
    var selectedUrl by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.fillMaxSize()) {
        // Barra superior con título
        TopAppBar(
            title = { Text("SocialPet", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFF4CAF50))
        )

        if (selectedUrl == null) {
            // Mostrar el texto introductorio y la lista de enlaces
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                // Texto introductorio
                Text(
                    text = "En esta sección encontrarás enlaces a sitios de interés para el cuidado de tu mascota. " +
                            "Desde blogs con consejos sobre salud y entrenamiento hasta tiendas con los mejores " +
                            "productos para consentir a tu amigo peludo.",
                    fontSize = 16.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp)) // Espacio entre el texto y los enlaces

                val links = listOf(
                    "Tienda" to "https://laika.com.co/",
                    "Cuidado y salud" to "https://www.desparasitaatumascota.es/blog",
                    "Entrenamiento" to "https://www.zooplus.es/magazine/perros/adiestramiento-canino/adiestrador-de-perros"
                )

                links.forEach { (title, url) ->
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue,
                        modifier = Modifier
                            .clickable { selectedUrl = url }
                            .padding(vertical = 8.dp)
                    )
                }
            }
        } else {
            // Cargar la web seleccionada
            Box(modifier = Modifier.weight(1f)) {
                AndroidView(
                    factory = { context ->
                        WebView(context).apply {
                            settings.javaScriptEnabled = true
                            webViewClient = object : WebViewClient() {
                                override fun shouldOverrideUrlLoading(
                                    view: WebView?,
                                    request: WebResourceRequest?
                                ): Boolean {
                                    view?.loadUrl(request?.url.toString())
                                    return false
                                }
                            }
                            loadUrl(selectedUrl!!)
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
