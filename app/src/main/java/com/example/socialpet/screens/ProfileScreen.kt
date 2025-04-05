import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.socialpet.R

@Composable
fun ProfileScreen(navController: NavHostController) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 100.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "MI PERFIL",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF009688)
            )

            Spacer(modifier = Modifier.height(24.dp))


            Image(
                painter = painterResource(id = R.drawable.dog),
                contentDescription = "Imagen de Lucas",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(20.dp))


            Text(
                text = "Lucas",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.height(20.dp))


            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Especie y raza: ")
                    }
                    append("Golden Retriever\n")

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
                },
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(20.dp))


            Text(
                text = "Hola humanos mi nombre es\n" +
                        "Lucas, soy un hermoso perrito\n" +
                        "Golden Retriever lleno de energía y\n" +
                        "amor. Amo las siestas, los\n" +
                        "paseos y las golosinas.\n" +
                        "Sígueme para ver mis\n" +
                        "aventuras 🐾❤️",
                fontSize = 16.sp
            )
        }
    }
}
