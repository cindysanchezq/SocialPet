package com.example.socialpet.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.socialpet.VideoPlayer

@Composable
fun VideosScreen(navController: NavHostController) {
    val isFullscreen = remember { mutableStateOf(false) }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = if (isFullscreen.value) 0.dp else 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (!isFullscreen.value) {
                Text(
                    text = "MIS VIDEOS",
                    fontSize = 32.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    color = Color(0xFF009688),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            VideoPlayer(
                videoUrl = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
                isFullscreen = isFullscreen,
                modifier = if (isFullscreen.value) {
                    Modifier.fillMaxSize()
                } else {
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                }
            )

            if (!isFullscreen.value) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Lucas jugando en el parque",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                VideoPlayer(
                    videoUrl = "https://www.html5rocks.com/en/tutorials/video/basics/devstories.webm",
                    isFullscreen = remember { mutableStateOf(false) }, // independiente
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Lucas nadando en la piscina",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
            }
        }
    }
}
