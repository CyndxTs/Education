package dev.sportinghub.mobile.views.navbar.search


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.sportinghub.mobile.server.components.CategoryBar
import dev.sportinghub.mobile.server.components.HomeTopBar
import dev.sportinghub.mobile.server.components.ImageFromAssets
import dev.sportinghub.mobile.server.components.getImgDirSuffixByTheme
import dev.sportinghub.mobile.viewmodels.ClientVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(clientVM: ClientVM, navController: NavController) {
    // Estado del texto de búsqueda
    var searchText by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        // Fondo
        ImageFromAssets(
            imgDir = "app/BG_${getImgDirSuffixByTheme()}.png",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Top bars
        HomeTopBar(navController, clientVM)
        CategoryBar(clientVM)

        // Lista de publicaciones (mockeadas)
        val publicaciones = listOf(
            Pair(1, "Polos deportivos Addidas"),
            Pair(2, "Polos deportivos Nikke"),

            )


        val publicacionesFiltradas = publicaciones.filter { (_, nombre) ->
            nombre.contains(searchText, ignoreCase = true)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Lista de productos",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )

            // Input de búsqueda
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text("Buscar producto") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (publicacionesFiltradas.isEmpty()) {
                Text("No se encontraron productos.", color = Color.Gray)
            } else {
                publicacionesFiltradas.forEach { (id, nombre) ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(4.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(nombre, style = MaterialTheme.typography.bodyLarge)
                            Button(onClick = {
                                navController.navigate("publication_detail_screen/$id")
                            }) {
                                Text("Ver detalle")
                            }
                        }
                    }
                }
            }
        }
    }
}
