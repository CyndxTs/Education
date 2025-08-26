package dev.sportinghub.mobile.server.components

import android.app.Activity
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.sportinghub.mobile.model.posts.DetailedVariantUiModel
import dev.sportinghub.mobile.model.posts.Variant
import dev.sportinghub.mobile.model.posts.VariantColor
import dev.sportinghub.mobile.model.posts.VariantMedia
import dev.sportinghub.mobile.model.posts.VariantSize
import dev.sportinghub.mobile.navigation.Navigate
import dev.sportinghub.mobile.viewmodels.ClientVM

//
@Composable
fun InitApp(clientVM: ClientVM) {
    SetSystemTheme(isSystemInDarkTheme())
    Navigate(clientVM)
}
//
@Composable
fun HomeTopBar(navController: NavController, clientVM: ClientVM) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(getThemeColor())
            .padding(vertical = 8.dp, horizontal = 16.dp) // padding vertical del fondo
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageFromAssets(
                imgDir = "app/Logo_Icon.png",
                modifier = Modifier
                    .size(24.dp,24.dp)
                    .clickable {
                        navController.navigate("MyBag");
                    },
                contentScale = ContentScale.Fit
            )
            ImageFromAssets(
                imgDir = "app/Logo_Title.png",
                modifier = Modifier
                    .clickable {
                        navController.navigate("AboutUs");
                    }
            )
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notificaciones",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        navController.navigate("NotificationList")
                    },
                tint = getAppPrimaryColor()
            )
        }
    }
}
//
@Composable
fun CategoryBar(clientVM: ClientVM) {

}
//
@Composable
fun AppNavigationBar(navController: NavController, clientVM: ClientVM) {
    data class NavigationItem(val navRoute: String,val label: String,val unselectedIcon: ImageVector, val selectedIcon: ImageVector)
    val appPrimaryColor = getAppPrimaryColor()
    val items = when(clientVM.getClientType().toString().uppercase()) {
        "ADMIN" -> {
            listOf(
                NavigationItem("ReportsList","ReportsList",Icons.Outlined.Info,Icons.Filled.Info),
                NavigationItem("Search","Search",Icons.Outlined.Search,Icons.Filled.Search)
            )
        }
        "BRAND" -> {
            listOf(
                NavigationItem("OrdersList","OrdersList",Icons.Outlined.Edit,Icons.Filled.Edit),
                NavigationItem("Account","Account",Icons.Outlined.Person,Icons.Filled.Person)
            )
        }
        else -> {
            listOf(
                NavigationItem("Home","Home",Icons.Outlined.Home,Icons.Filled.Home),

                NavigationItem("Search","Search",Icons.Outlined.Search,Icons.Filled.Search),

                NavigationItem("MyBag","Carrito", Icons.Outlined.ShoppingCart, Icons.Filled.ShoppingCart),

                NavigationItem("Account","Account",Icons.Outlined.Person,Icons.Filled.Person)



            )
        }
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Column {
        Divider(
            thickness = 2.dp,
            color = getOppositeThemeColor()
        )
        NavigationBar(
            containerColor = getThemeColor()
        ) {
            items.forEach { item ->
                Log.d("NAV", "Ruta actual: $currentRoute")
                val selected = currentRoute == item.navRoute
                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        if (!selected) navController.navigate(item.navRoute) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                            contentDescription = item.navRoute
                        )
                    },
                    label = { Text(text = item.navRoute) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = appPrimaryColor,
                        unselectedIconColor = appPrimaryColor,
                        selectedTextColor = appPrimaryColor,
                        unselectedTextColor = appPrimaryColor,
                        indicatorColor = Color.Transparent,

                    )
                )
            }
        }
    }
}
//
@Composable
fun ImageFromAssets(imgDir: String,modifier: Modifier = Modifier,contentScale: ContentScale = ContentScale.Fit) {
    val context = LocalContext.current
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(Unit) {
        try {
            val assetManager = context.assets
            val inputStream = assetManager.open(imgDir)
            imageBitmap = BitmapFactory.decodeStream(inputStream).asImageBitmap()
            inputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    imageBitmap?.let { image ->
        Image(
            bitmap = image,
            contentDescription = imgDir.substringAfterLast("/").substringBefore("."),
            modifier = modifier,
            contentScale = contentScale
        )
    }
}
//
@Composable
fun animate_ScaleZoom(initValue: Float, targetValue: Float, duration: Int): Float {
    var zooming by remember { mutableStateOf(false) }
    val scaleVal by animateFloatAsState(
        targetValue = if (zooming) targetValue else initValue,
        animationSpec = tween(durationMillis = duration)
    )
    LaunchedEffect(Unit) {
        zooming = true
    }
    return scaleVal
}
//
@Composable
fun getAppPrimaryColor(): Color {
    return Color(0xFFF27C38);
}
//
@Composable
fun getThemeColor(): Color {
    return if(isSystemInDarkTheme()) Color.Black else Color.White
}
//
@Composable
fun getOppositeThemeColor(): Color {
    return if(isSystemInDarkTheme()) Color.White else Color.Black
}
//
@Composable
fun getImgDirSuffixByTheme(): String {
    return if(isSystemInDarkTheme()) "DM" else "LM"
}
//
@Composable
fun SetSystemTheme(isDarkMode: Boolean) {
    val color = if (isDarkMode) Color.Black else Color.White
    val context = LocalContext.current
    val activity = context as Activity
    val window = activity.window
    val insetsController = WindowInsetsControllerCompat(window, window.decorView)
    window.statusBarColor = color.toArgb()
    insetsController.isAppearanceLightStatusBars = !isDarkMode
    window.navigationBarColor = color.toArgb()
    insetsController.isAppearanceLightNavigationBars = !isDarkMode
}


//reutilizacion para card de detalle de publicacion
@Composable
fun AssetImage(assetName: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    AndroidView(
        factory = {
            ImageView(it).apply {
                val assetManager = context.assets
                val inputStream = assetManager.open(assetName)
                val drawable = Drawable.createFromStream(inputStream, null)
                setImageDrawable(drawable)
                scaleType = ImageView.ScaleType.FIT_CENTER
            }
        },
        modifier = modifier
    )
}

/*
@Composable
fun VariantCard(variantDetail: DetailedVariantUiModel) {
    val variant = variantDetail.variant
    val size = variantDetail.size?.name ?: "Sin talla"
    val color = variantDetail.color?.name ?: "Sin color"
    val mediaUrl = variantDetail.media?.mediaUrl ?: ""

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Precio: S/ ${variant.price}")
            Text("Talla: $size")
            Text("Color: $color")
            if (mediaUrl.isNotBlank()) {
                AssetImage(
                    assetName = mediaUrl,
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                )
            } else {
                Text("Sin imagen")
            }
        }
    }
}

*/
@Composable
fun VariantCard(
    variant: Variant,
    variantSize: VariantSize,
    variantColor: VariantColor,
    variantMedia: List<VariantMedia>,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val mediaUrl = variantMedia.first().mediaUrl
    val borderColor = if (isSelected) Color.Red else Color.LightGray

    Card(
        modifier = Modifier
            .width(180.dp)
            .clickable { onClick() }
            .border(2.dp, borderColor, RoundedCornerShape(12.dp)),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("Precio: S/ ${variant.price}")
            Text("Talla: ${variantSize.name}")
            Text("Color: ${variantColor.name}")
            Spacer(Modifier.height(8.dp))
            if (mediaUrl.isNotBlank()) {
                ImageFromAssets(
                    imgDir = mediaUrl,
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                )
            } else {
                Text("Sin imagen")
            }
        }
    }
}
