package com.example.socialpet.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import com.example.socialpet.VideoPlayer

data class VideoItem(
    val uri: Uri,
    val title: String,
    val description: String
)
@Composable
fun VideosScreen(navController: NavHostController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val videos = remember {
        mutableStateListOf(
            VideoItem(
                uri = Uri.parse("https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"),
                title = "Lucas jugando en el parque",
                description = "Divirtiéndose con sus amigos"
            ),
            VideoItem(
                uri = Uri.parse("https://www.html5rocks.com/en/tutorials/video/basics/devstories.webm"),
                title = "Lucas nadando en la piscina",
                description = "Saltos, chapoteo y diversión"
            )
        )
    }

    var newVideoUri by remember { mutableStateOf<Uri?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var tempTitle by remember { mutableStateOf("") }
    var tempDescription by remember { mutableStateOf("") }

    var fullscreenVideo by remember { mutableStateOf<VideoItem?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            newVideoUri = it
            showDialog = true
        }
    }

    Scaffold { innerPadding ->
        fullscreenVideo?.let { video ->

            Box(modifier = Modifier.fillMaxSize()) {
                VideoPlayer(
                    videoUrl = video.uri.toString(),
                    isFullscreen = true,
                    modifier = Modifier.fillMaxSize(),
                    onToggleFullscreen = {
                        fullscreenVideo = null
                    }
                )

                IconButton(
                    onClick = { fullscreenVideo = null },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                ) {
                    Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = Color.White)
                }
            }
        } ?: Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "MIS VIDEOS",
                fontSize = 32.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                color = Color(0xFF009688)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF009688)),
                onClick = { launcher.launch("video/*") }) {
                Text("Agregar Video")
            }

            Spacer(modifier = Modifier.height(20.dp))

            videos.forEach { video ->
                VideoPlayer(
                    videoUrl = video.uri.toString(),
                    isFullscreen = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    onToggleFullscreen = {
                        fullscreenVideo = video
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = video.title,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = video.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
    }

    if (showDialog && newVideoUri != null) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
                newVideoUri = null
                tempTitle = ""
                tempDescription = ""
            },
            title = { Text("Detalles del Video") },
            text = {
                Column {
                    OutlinedTextField(
                        value = tempTitle,
                        onValueChange = { tempTitle = it },
                        label = { Text("Título") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = tempDescription,
                        onValueChange = { tempDescription = it },
                        label = { Text("Descripción") }
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    videos.add(VideoItem(newVideoUri!!, tempTitle, tempDescription))
                    showDialog = false
                    newVideoUri = null
                    tempTitle = ""
                    tempDescription = ""
                }) {
                    Text("Guardar")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                    newVideoUri = null
                    tempTitle = ""
                    tempDescription = ""
                }) {
                    Text("Cancelar")
                }
            }
        )
    }
}
