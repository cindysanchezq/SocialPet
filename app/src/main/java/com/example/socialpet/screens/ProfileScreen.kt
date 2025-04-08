package com.example.socialpet.screens

import android.R.id.bold
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.socialpet.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = viewModel()
) {
    val imageUri by viewModel.imageUri.collectAsState()
    val name by viewModel.name.collectAsState()
    val age by viewModel.age.collectAsState()
    val weight by viewModel.weight.collectAsState()
    val breed by viewModel.breed.collectAsState()
    val owner by viewModel.owner.collectAsState()
    val description by viewModel.description.collectAsState()

    var isEditing by remember { mutableStateOf(false) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { viewModel.setImageUri(it) }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 32.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(70.dp))
            Text(
                text = name,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF009688),
                modifier = Modifier.padding(bottom = 30.dp)
            )

            Box(contentAlignment = Alignment.BottomEnd) {
                Image(
                    painter = imageUri?.let { rememberAsyncImagePainter(it) }
                        ?: painterResource(id = com.example.socialpet.R.drawable.dog),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(190.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = "Cambiar foto",
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(32.dp)
                        .padding(5.dp)
                        .clickable { imagePickerLauncher.launch("image/*") },
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            if (isEditing) {
                EditableField("Nombre", name) { viewModel.setName(it) }
                EditableField("Edad", age) { viewModel.setAge(it) }
                EditableField("Peso", weight) { viewModel.setWeight(it) }
                EditableField("Raza", breed) { viewModel.setBreed(it) }
                EditableField("Dueño", owner) { viewModel.setOwner(it) }
                EditableField("Descripción", description, singleLine = false) {
                    viewModel.setDescription(it)
                }
            } else {
                InfoCard("🎂", age)
                InfoCard("💪", weight)
                InfoCard("🐾", breed)
                InfoCard("👤", owner)
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = description,
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center // ⭐ Centrado
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { isEditing = !isEditing },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF009688))
            ) {
                Text(
                    text = if (isEditing) "Guardar" else "Editar",
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun InfoCard(emoji: String, text: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEFEFEF))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center // ⭐ Centro horizontal
        ) {
            Text(text = "$emoji $text", fontSize = 16.sp)
        }
    }
}

@Composable
fun EditableField(label: String, value: String, singleLine: Boolean = true, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        singleLine = singleLine
    )
}
