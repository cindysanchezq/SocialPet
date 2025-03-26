package com.example.socialpet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontWeight

@Composable
fun AppDrawer(navController: NavHostController, closeDrawer: () -> Unit) {

    val drawerGreen = Color(0xFF80CBC4)

    Column(
        modifier = Modifier
            .width(180.dp)
            .fillMaxHeight()
            .background(drawerGreen)
            .padding(start = 0.dp, top = 70.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        Row(
            modifier = Modifier.padding(start = 20.dp),
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Text(
                text = "SocialPet",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold

            )
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                imageVector = Icons.Outlined.Pets,
                contentDescription = "Huella",
                tint = Color.White,
                modifier = Modifier.size(35.dp)

            )
        }


        DrawerItem("Perfil") {
            navController.navigate(Screen.Profile.route)
            closeDrawer()
        }
        DrawerItem("Fotos") {
            navController.navigate(Screen.Photos.route)
            closeDrawer()
        }
        DrawerItem("Videos") {
            navController.navigate(Screen.Videos.route)
            closeDrawer()
        }
        DrawerItem("Web") {
            navController.navigate(Screen.Web.route)
            closeDrawer()
        }
        DrawerItem("Botones") {
            navController.navigate(Screen.Button.route)
            closeDrawer()
        }
    }
}

@Composable
fun DrawerItem(text: String, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val backgroundColor = if (isPressed) Color(0xFF009688) else Color.Transparent

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .padding(vertical = 60.dp, horizontal = 30.dp)
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}