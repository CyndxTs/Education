package dev.sportinghub.mobile.views.orders


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import dev.sportinghub.mobile.server.components.ImageFromAssets
import dev.sportinghub.mobile.viewmodels.ClientVM
import java.math.BigDecimal
import java.math.RoundingMode


@Composable
fun MyBagScreen(clientVM: ClientVM, navController: NavController) {
    val carrito by clientVM.carrito.collectAsState()
    val mensaje by clientVM.mensajeCarrito.collectAsState()
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            // 🔢 Contador
            Text(
                text = "Carrito: ${carrito.sumOf { it.cantidad }} artículos",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                modifier = Modifier.padding(16.dp)
            )

            mensaje?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF4CAF50),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            if (carrito.isEmpty()) {
                Text(
                    text = "Tu carrito está vacío.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray,
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 140.dp), // espacio para que no tape el resumen
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        items = carrito,
                        key = { it.variant.id ?: it.hashCode() }
                    ) { item ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(2.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                item.media.first().let { mediaUrl ->
                                    ImageFromAssets(
                                        imgDir = "$mediaUrl",
                                        modifier = Modifier
                                            .size(80.dp)
                                            .padding(end = 12.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(item.publicationPost.title, style = MaterialTheme.typography.titleMedium)
                                    Text("Talla: ${item.size?.name ?: "N/A"}")
                                    Text("Color: ${item.color?.name ?: "N/A"}")
                                    Text("Cantidad: ${item.cantidad}")
                                    Text("Precio: S/ ${item.variant.price * item.cantidad}")
                                }
                                IconButton(onClick = { clientVM.eliminarDelCarrito(item) }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color.Red)
                                }
                            }
                        }
                    }
                }
            }
        }

        // 🔽 Resumen del pedido fijo al fondo
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            tonalElevation = 4.dp,
            shadowElevation = 8.dp,
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = !expanded },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Resumen del pedido",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black
                    )
                    Icon(
                        imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = if (expanded) "Colapsar" else "Expandir"
                    )
                }

                AnimatedVisibility(visible = expanded) {
                    val total = carrito.sumOf {
                        BigDecimal(it.variant.price.toDouble() * it.cantidad)
                    }.setScale(2, RoundingMode.HALF_UP)

                    Column(modifier = Modifier.padding(top = 8.dp)) {
                        Text("Total: S/ $total")
                        Text(
                            text = "Descuento: No Aplica",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Gray
                        )
                        Button(
                            onClick = { /* por ahora no hace nada */ },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = false
                        ) {
                            Text("Hacer pedido")
                        }
                    }
                }
            }
        }
    }
}

