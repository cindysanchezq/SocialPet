import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter

@Composable
fun ButtonScreen(navController: NavHostController) {
    val context = LocalContext.current

    var isFollowing by remember { mutableStateOf(false) }
    var showCommentBox by remember { mutableStateOf(false) }
    var commentText by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }


    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(150.dp))

        Text(
            text = "Botones Interactivos",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF009688)
        )



        Spacer(modifier = Modifier.height(30.dp))


        Button(
            onClick = {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "¡Mira mi perfil en SocialPet! https://socialpet.com/perfil/lucas123"
                    )
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, "Compartir perfil con:")
                context.startActivity(shareIntent)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Compartir mi perfil")
        }


        Spacer(modifier = Modifier.height(30.dp))


        Button(
            onClick = {
                imagePickerLauncher.launch("image/*")
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9C27B0)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Subir foto")
        }


        selectedImageUri?.let { uri ->
            Spacer(modifier = Modifier.height(16.dp))
            Text("Vista previa de la imagen:", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = rememberAsyncImagePainter(uri),
                contentDescription = "Imagen seleccionada",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}