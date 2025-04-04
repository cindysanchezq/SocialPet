package com.example.socialpet.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale

import com.example.socialpet.R






@Composable
fun ProfileScreen(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart // Alinea el contenido en la parte superior izquierda
    ) {
        Column(
            modifier = Modifier
                .padding(86.dp) // Margen para que no esté pegado a los bordes
                .fillMaxWidth()
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Perfil: ")
                    }
                    append("En esta sesión encontrarás toda la información sobre tu mascota.\n" +
                            "Puedes observar su nombre, raza, edad y una foto de perfil para que " +
                            "todos la conozcan.")
                }
            )



            Spacer(modifier = Modifier.height(8.dp)) // Espacio entre textos

            Text(
                text = "Lucas",
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp)) // Espacio entre textos

           // Image(
            //    painter = painterResource(id = R.drawable.dog), // Asegúrate de tener la imagen en tu carpeta de recursos
            //    contentDescription = "Imagen de Lucas",
            //    modifier = Modifier
            //        .fillMaxWidth()
            //        .height(200.dp), // Ajusta el tamaño según tus necesidades
            //    contentScale = ContentScale.Crop
        //    )



            Spacer(modifier = Modifier.height(8.dp)) // Espacio entre textos

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Especie y raza: ")
                    }
                    append("Boston Terrier\n")

                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Edad: ")
                    }
                    append("2 años\n")

                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Peso: ")
                    }
                    append("9 Kg\n")

                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Dueño: ")
                    }
                    append("Gabriel Torres")
                }
            )



            Spacer(modifier = Modifier.height(8.dp)) // Espacio entre textos

            Text(
                text = "Hola humanos mi nombre es\n" +
                        "lucas soy un hermoso perrito\n" +
                        "Boston lleno de energia y\n" +
                        "amor. Amo las siestas, los\n" +
                        "paseos y las golosinas.\n" +
                        "Sigueme para ver mis\n" +
                        "aventuras \uD83D\uDC3E♥\uFE0F"
            )

        }
    }
}
