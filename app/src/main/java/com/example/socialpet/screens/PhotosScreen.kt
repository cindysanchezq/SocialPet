package com.example.socialpet.screens

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.socialpet.R
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import java.io.InputStream

sealed class PhotoData {
    data class Resource(val resId: Int) : PhotoData()
    data class Bitmap(val bitmap: ImageBitmap) : PhotoData()
}

data class Photo(val image: PhotoData, val description: String, val independiente: String)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun PhotosScreen(navController: NavHostController) {
    val context = LocalContext.current
    val photos = remember {
        mutableStateListOf(
            Photo(PhotoData.Resource(R.drawable.image1), "Lucas, el rey del parque", "Siempre corriendo tras las pelotas y haciendo nuevos amigos."),
            Photo(PhotoData.Resource(R.drawable.image2), "Rocky y Luna", "Jugando todo el día. Hermanos y mejores amigos."),
            Photo(PhotoData.Resource(R.drawable.image3), "Thor y Nala", "Juntos para proteger su hogar y compartir siestas.")
        )
    }
    var selectedIndex by remember { mutableStateOf<Int?>(null) }

    var imageToAdd by remember { mutableStateOf<ImageBitmap?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    var tempTitle by remember { mutableStateOf("") }
    var tempDescription by remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            val inputStream: InputStream? = context.contentResolver.openInputStream(it)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            bitmap?.let {
                imageToAdd = it.asImageBitmap()
                showDialog = true
            }
        }
    }

    if (selectedIndex == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(120.dp))
            Text(
                text = "MIS FOTOS",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF009688),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { launcher.launch("image/*") },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF009688))
            ) {
                Text("Agregar Foto")
            }

            Spacer(modifier = Modifier.height(24.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(photos) { index, photo ->
                    PhotoCard(photo = photo) {
                        selectedIndex = index
                    }
                }
            }
        }
    } else {
        PhotoDetailPager(
            photos = photos,
            startIndex = selectedIndex!!,
            onClose = { selectedIndex = null }
        )
    }


    if (showDialog && imageToAdd != null) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
                tempTitle = ""
                tempDescription = ""
                imageToAdd = null
            },
            confirmButton = {
                Button(onClick = {
                    photos.add(
                        Photo(
                            image = PhotoData.Bitmap(imageToAdd!!),
                            description = tempTitle,
                            independiente = tempDescription
                        )
                    )
                    showDialog = false
                    tempTitle = ""
                    tempDescription = ""
                    imageToAdd = null
                }) {
                    Text("Guardar")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                    tempTitle = ""
                    tempDescription = ""
                    imageToAdd = null
                }) {
                    Text("Cancelar")
                }
            },
            title = { Text("Detalles de la Foto") },
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
            }
        )
    }
}
@Composable
fun PhotoCard(photo: Photo, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        when (val image = photo.image) {
            is PhotoData.Resource -> Image(
                painter = painterResource(id = image.resId),
                contentDescription = photo.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            is PhotoData.Bitmap -> Image(
                bitmap = image.bitmap,
                contentDescription = photo.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun PhotoDetailPager(photos: List<Photo>, startIndex: Int, onClose: () -> Unit) {
    val pagerState = rememberPagerState(initialPage = startIndex)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            count = photos.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            val photo = photos[page]
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(150.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    when (val image = photo.image) {
                        is PhotoData.Resource -> Image(
                            painter = painterResource(id = image.resId),
                            contentDescription = photo.description,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        is PhotoData.Bitmap -> Image(
                            bitmap = image.bitmap,
                            contentDescription = photo.description,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(50.dp))
                Text(photo.description, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(photo.independiente, fontSize = 16.sp)
            }
        }


        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onClose,
            modifier = Modifier
                .padding(bottom = 150.dp)
        ) {
            Text("Volver a la galería")
        }
    }
}