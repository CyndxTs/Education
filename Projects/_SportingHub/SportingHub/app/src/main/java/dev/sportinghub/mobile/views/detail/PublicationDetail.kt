package dev.sportinghub.mobile.views.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.sportinghub.mobile.model.posts.BagItem
import dev.sportinghub.mobile.model.posts.DetailedVariantUiModel
import dev.sportinghub.mobile.model.posts.PublicationPost
import dev.sportinghub.mobile.model.posts.Variant
import dev.sportinghub.mobile.model.posts.VariantColor
import dev.sportinghub.mobile.model.posts.VariantMedia
import dev.sportinghub.mobile.model.posts.VariantSize
import dev.sportinghub.mobile.server.components.ImageFromAssets
import dev.sportinghub.mobile.server.components.VariantCard
import dev.sportinghub.mobile.server.components.getAppPrimaryColor
import dev.sportinghub.mobile.server.components.getImgDirSuffixByTheme
import dev.sportinghub.mobile.server.components.getOppositeThemeColor
import dev.sportinghub.mobile.viewmodels.ClientVM
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PublicationDetailScreen(clientVM: ClientVM, navController: NavController, id: Int) {
    val snackbarHostState = remember { SnackbarHostState() }
    var mostrarBotonCarrito by remember { mutableStateOf(false) }
    var mensajeTemporal by remember { mutableStateOf<String?>(null) }
    var cantidad by remember { mutableStateOf(1) }
    var isAddingToCart by remember { mutableStateOf(false) }
    var publicationPost by remember { mutableStateOf<PublicationPost?>(null) }
    var variants by remember { mutableStateOf<MutableList<Variant>>(mutableListOf()) }
    var selectedVariant by remember { mutableStateOf<Variant?>(null) }
    var selectedVariantMedia by remember { mutableStateOf<MutableList<VariantMedia>>(mutableListOf()) }
    var sizeOptions by remember { mutableStateOf<MutableList<VariantSize>>(mutableListOf()) }
    var selectedSizeOption by remember { mutableStateOf<Int?>(null) }
    var expandSizeOptionsDDL by remember { mutableStateOf(false) }
    var colorOptions by remember { mutableStateOf<MutableList<VariantColor>>(mutableListOf()) }
    var selectedColorOption by remember { mutableStateOf<Int?>(null) }
    var expandColorOptionsDDL by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    // Corrutina para cargar los datos al montar el composable
    LaunchedEffect(id) {
        publicationPost = clientVM.getPublicationById(id)
        variants = clientVM.getVariantsByPublicationId(id)
        selectedVariant = variants.firstOrNull()
        sizeOptions = clientVM.getSizesByVariants(variants)
        colorOptions = clientVM.getColorsByVariants(variants)
        selectedVariant?.let {
            selectedVariantMedia = clientVM.getMediasByVariantId(it.id ?: 1)
        }
    }

    val filteredVariants = variants.filter {
        (selectedSizeOption == null || it.sizeId == selectedSizeOption) &&
                (selectedColorOption == null || it.colorId == selectedColorOption)
    }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            ImageFromAssets(
                imgDir = "app/BG_${getImgDirSuffixByTheme()}.png",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Botón de volver
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, top = 8.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(50))
                            .padding(8.dp)
                            .clickable { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.padding(8.dp))

                // Tarjeta principal
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f)),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if(publicationPost != null) {
                            Text(
                                text = publicationPost!!.title,
                                color = Color.Black,
                                style = MaterialTheme.typography.headlineSmall,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.padding(4.dp))
                            Text(
                                text = publicationPost!!.description ?: "Sin descripción",
                                color = Color.DarkGray,
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                            Text(
                                text = "Descuento: ${publicationPost!!.discount ?: 0f}%",
                                color = Color(0xFF388E3C),
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Center
                            )
                        } else CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // FILTROS
                // Talla
                Box(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
                    OutlinedButton(
                        onClick = { expandSizeOptionsDDL = true },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = if(selectedSizeOption != null ) sizeOptions.get(selectedSizeOption!!).name else "Filtrar por talla")
                    }

                    DropdownMenu(
                        expanded = expandSizeOptionsDDL,
                        onDismissRequest = { expandSizeOptionsDDL = false }
                    ) {
                        sizeOptions.forEach { sizeOption ->
                            DropdownMenuItem(
                                text = { Text(sizeOption.name) },
                                onClick = {
                                    selectedSizeOption = sizeOption.id
                                    expandSizeOptionsDDL = false
                                }
                            )
                        }
                        Divider()
                        DropdownMenuItem(
                            text = { Text("Quitar filtro") },
                            onClick = {
                                selectedSizeOption = null
                                expandSizeOptionsDDL = false
                            }
                        )
                    }
                }

                // Color
                Box(modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)) {
                    OutlinedButton(
                        onClick = { expandColorOptionsDDL = true },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = if(selectedColorOption != null) colorOptions.get(selectedColorOption!!).name else "Filtrar por color")
                    }

                    DropdownMenu(
                        expanded = expandColorOptionsDDL,
                        onDismissRequest = { expandColorOptionsDDL = false }
                    ) {
                        colorOptions.forEach { color ->
                            DropdownMenuItem(
                                text = { Text(color.name) },
                                onClick = {
                                    selectedColorOption = color.id
                                    expandColorOptionsDDL = false
                                }
                            )
                        }
                        Divider()
                        DropdownMenuItem(
                            text = { Text("Quitar filtro") },
                            onClick = {
                                selectedColorOption = null
                                expandColorOptionsDDL = false
                            }
                        )
                    }
                }

                if (filteredVariants.isNotEmpty()) {
                    Text(
                        text = "Variantes disponibles:",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(230.dp)
                            .padding(bottom = 16.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(filteredVariants) { variant ->
                            val isSelected = selectedVariant == variant

                            VariantCard(
                                variant = variant,
                                variantSize = sizeOptions.get(selectedSizeOption?:0),
                                variantColor = colorOptions.get(selectedColorOption?:0),
                                variantMedia = selectedVariantMedia,
                                isSelected = isSelected,
                                onClick = {
                                    selectedVariant = variant
                                    cantidad = 1
                                }
                            )
                        }
                    }
                } else {
                    Text(
                        text = "No hay variantes disponibles",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }


                // Cantidad (si hay variante seleccionada)
                if (selectedVariant != null) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("Cantidad: ", style = MaterialTheme.typography.bodyLarge)
                        IconButton(onClick = { if (cantidad > 1) cantidad-- }) {
                            Icon(Icons.Default.Remove, contentDescription = "Restar")
                        }
                        Text(cantidad.toString(), style = MaterialTheme.typography.bodyLarge)
                        IconButton(onClick = { cantidad++ }) {
                            Icon(Icons.Default.Add, contentDescription = "Sumar")
                        }
                    }
                }

                // BOTÓN
                Button(
                    onClick = {
                        selectedVariant?.let { variant ->
                            clientVM.agregarAlCarrito(BagItem(variant = variant, size = sizeOptions.get(selectedSizeOption?:0), color = colorOptions.get(selectedColorOption?:0), media = selectedVariantMedia, cantidad = cantidad, publicationPost = publicationPost!!))

                            mostrarBotonCarrito = true
                            mensajeTemporal = "Se agregó $cantidad al carrito"

                            // Oculta el mensaje después de 1 segundo
                            scope.launch {
                                delay(2000)
                                mensajeTemporal = null
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = getAppPrimaryColor()),
                    enabled = selectedVariant != null
                ) {
                    Text(
                        text = if (selectedVariant != null) "Agregar $cantidad al carrito"
                        else "Selecciona una variante",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
                if (mensajeTemporal != null) {
                    Text(
                        text = mensajeTemporal!!,
                        color = Color(0xFF388E3C),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 8.dp)

                    )
                }

                if (mostrarBotonCarrito) {
                    Button(
                        onClick = { navController.navigate("MyBag") {
                            popUpTo("Home") { inclusive = false }  // O el nombre de tu ruta de inicio
                            launchSingleTop = true
                        } },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                    ) {
                        Text("Ir al carrito", color = Color.White)
                    }
                }

            }
        }
    }

}
