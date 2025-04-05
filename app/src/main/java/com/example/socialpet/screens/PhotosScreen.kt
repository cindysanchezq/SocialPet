package com.example.socialpet.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.socialpet.R
import androidx.navigation.NavHostController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

data class Photo(val imageRes: Int, val description: String, val independiente: String)

@Composable
fun PhotosScreen(navController: NavHostController) {
    val photos = listOf(
        Photo(R.drawable.image1, "Lucas, el rey del parque", "Siempre corriendo tras las pelotas y haciendo nuevos amigos."),
        Photo(R.drawable.image2, "Rocky y Luna", "Jugando todo el día. Hermanos y mejores amigos."),
        Photo(R.drawable.image3, "Thor y Nala", "Juntos para proteger su hogar y compartir siestas."),
        Photo(R.drawable.image4, "Thor y Nala", "Juntos para proteger su hogar y compartir siestas."),
        Photo(R.drawable.image5, "Thor y Nala", "Juntos para proteger su hogar y compartir siestas."),
        Photo(R.drawable.image6, "Thor y Nala", "Juntos para proteger su hogar y compartir siestas.")
    )

    var selectedPhoto by remember { mutableStateOf<Photo?>(null) }

    if (selectedPhoto == null) {
        // Galería
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

            Spacer(modifier = Modifier.height(50.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(photos) { photo ->
                    PhotoCard(photo = photo) {
                        selectedPhoto = it
                    }
                }
            }
        }
    } else {
        // Vista de detalle
        PhotoDetail(photo = selectedPhoto!!) {
            selectedPhoto = null
        }
    }
}

@Composable
fun PhotoCard(photo: Photo, onClick: (Photo) -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { onClick(photo) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column {

            Image(
                painter = painterResource(id = photo.imageRes),
                contentDescription = photo.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp))
            )
        }
    }
}

@Composable
fun PhotoDetail(photo: Photo, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(top = 200.dp), // ✅ separa de la barra superior
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(16.dp)) // espacio adicional si quieres más separación

        Image(
            painter = painterResource(id = photo.imageRes),
            contentDescription = photo.description,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = photo.description,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = photo.independiente,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onBack) {
            Text("Volver a la galería")
        }
    }
}


