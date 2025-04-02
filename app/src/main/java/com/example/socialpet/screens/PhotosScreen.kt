package com.example.socialpet.screens

import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable //luego se utilizara
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.socialpet.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp


@Composable

fun PhotosScreen(navController: NavHostController) {

    val photos = listOf(
        Photo(R.drawable.image1, "Descripción de la imagen 1","Lucas, el rey del parque. Siempre corriendo tras las pelotas y haciendo nuevos amigos."),
        Photo(R.drawable.image2, "Descripción de la imagen 2","Rocky y Luna, jugando todo el día. Hermanos y mejores amigos. #HermanosPerrunos"),
        Photo(R.drawable.image3, "Descripción de la imagen 3","Thor y Nala, juntos para proteger su hogar y compartir siestas. #HermanosEnCasa")
    )

    var selectedPhoto by remember { mutableStateOf<Photo?>(null) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(30.dp),
            //verticalArrangement = Arrangement.Center, //alinea texto al lado
            horizontalAlignment = Alignment.CenterHorizontally //alinea el texto en el centro
        ) {
            if (selectedPhoto == null) {

                Spacer(modifier = Modifier.height(60.dp)) // Espacio entre navbar y texto galeria de fotos

                // Título
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Galería de Fotos ")
                        }
                    },
                    fontSize = 25.sp
                )
                Spacer(modifier = Modifier.height(6.dp)) // Espacio entre navbar y texto galeria de fotos

            }
            // Galería de fotos
            if (selectedPhoto == null) {
                LazyColumn {
                    items(photos.size) { index ->
                        // Mostrar cada foto con un texto distinto
                        val photo = photos[index]
                        PhotoItem(photo = photos[index]) { selectedPhoto = it }
                        // Descripción adicional
                        Text(
                            text = photo.independiente,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(8.dp)) // Espacio debajo de cada imagen
                    }
                }
            } /*else {
                PhotoDetail(photo = selectedPhoto!!) { selectedPhoto = null }
            }*/
        }

    }


}

//Estilos para la galeria de fotos y tamaño
@Composable
fun PhotoItem(photo: Photo, onClick: (Photo) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(5.dp)/*.clickable { onClick(photo) }*/, // me deja espaciado entre imagenes
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Image(
            painter = painterResource(id = photo.imageRes),
            contentDescription = photo.description,
            modifier = Modifier.fillMaxWidth()
                .height(190.dp) // Controla la altura de la imagen
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

//Cuando se le da click se desplaza el detalle
/*@Composable
fun PhotoDetail(photo: Photo, onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = photo.imageRes),
            contentDescription = photo.description,
            modifier = Modifier.fillMaxWidth().height(300.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = photo.description, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack) {
            Text("Volver a la galería")
        }
    }
}*/

data class Photo(val imageRes: Int, val description: String, val independiente: String)