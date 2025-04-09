package com.example.socialpet.screens

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun WebScreen(navController: NavHostController) {
    var selectedUrl by remember { mutableStateOf<String?>(null) }
    var customUrl by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(
                    "SocialPet",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFF4CAF50))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "WEB",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF009688)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Encuentra aquí las mejores recomendaciones para ti en cuidado animal.",
                fontSize = 18.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))


            Text(
                text = "Visita una página personalizada",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF1976D2)
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = customUrl,
                onValueChange = { customUrl = it },
                label = { Text("Escribe la URL (https://...)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF009688)),
                onClick = {
                if (customUrl.isNotBlank()) {
                    coroutineScope.launch {
                        selectedUrl = null
                        delay(50)
                        selectedUrl = customUrl
                        delay(300)
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            }) {
                Text("Cargar página")
            }

            Spacer(modifier = Modifier.height(32.dp))


            val links = listOf(
                "Tienda" to "https://laika.com.co/",
                "Cuidado y salud" to "https://www.desparasitaatumascota.es/blog",
                "Entrenamiento" to "https://www.zooplus.es/magazine/perros/adiestramiento-canino/adiestrador-de-perros"
            )

            Text(
                text = "Recomendaciones populares:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            links.forEach { (title, url) ->
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1976D2),
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clickable {
                            coroutineScope.launch {
                                selectedUrl = null
                                delay(50)
                                selectedUrl = url
                                delay(300)
                                bringIntoViewRequester.bringIntoView()
                            }
                        }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 🔹 Visor Web
            if (selectedUrl != null) {
                Text(
                    text = "Contenido Web",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Box(
                    modifier = Modifier
                        .bringIntoViewRequester(bringIntoViewRequester)
                        .fillMaxWidth()
                ) {
                    AndroidView(
                        factory = { context ->
                            WebView(context).apply {
                                settings.javaScriptEnabled = true
                                isVerticalScrollBarEnabled = true
                                isHorizontalScrollBarEnabled = false
                                setOnTouchListener { v, event ->
                                    v.parent.requestDisallowInterceptTouchEvent(true)
                                    false
                                }
                                webViewClient = WebViewClient()
                                loadUrl(selectedUrl!!)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(600.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(onClick = {
                    selectedUrl = null
                }) {
                    Text("Cerrar contenido")
                }
            }
        }
    }
}
