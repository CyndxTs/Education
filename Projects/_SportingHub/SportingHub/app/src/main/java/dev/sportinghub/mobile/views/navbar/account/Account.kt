package dev.sportinghub.mobile.views.navbar.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.sportinghub.mobile.server.components.ImageFromAssets
import dev.sportinghub.mobile.server.components.getImgDirSuffixByTheme
import dev.sportinghub.mobile.viewmodels.ClientVM

@Composable
fun AccountScreen(clientVM: ClientVM, navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ImageFromAssets(
            imgDir = "app/BG_${getImgDirSuffixByTheme()}.png",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
    val account = clientVM.getClient()
    val username = account!!.nickname
    val bio = account.bio
    val ventas = "1.2mil"
    val publicaciones = 12
    val compras = 76
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Íconos superiores
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                tint = MaterialTheme.colorScheme.primary
            )
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Foto de perfil y nombre
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Box {
                ImageFromAssets(
                    imgDir = "accounts/fotoPerfilv2.png",
                    modifier = Modifier
                        .size(100.dp) // puedes ajustar el tamaño aquí
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

            }

            Text(username, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(bio?:"...", fontSize = 14.sp, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(4.dp))

        }

        Spacer(modifier = Modifier.height(12.dp))

        // Métricas
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Ventas", fontSize = 12.sp)
                Text(ventas, fontWeight = FontWeight.Bold)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Publicaciones", fontSize = 12.sp)
                Text("$publicaciones", fontWeight = FontWeight.Bold)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Compras", fontSize = 12.sp)
                Text("$compras", fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Botones
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { navController.navigate("Load/ProfileSettings") }) {
                Text("Personalizar")
            }
            Button(onClick = {  navController.navigate("Load/Nueva_Publicacion") }) {
                Text("Nueva Publicacion")
            }
        }



    }

}



