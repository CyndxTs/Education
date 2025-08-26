package dev.sportinghub.mobile.views.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.sportinghub.mobile.server.components.ImageFromAssets
import dev.sportinghub.mobile.server.components.animate_ScaleZoom
import dev.sportinghub.mobile.server.components.getImgDirSuffixByTheme
import dev.sportinghub.mobile.viewmodels.ClientVM
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

@Composable
fun LoadScreen(clientVM: ClientVM,navController: NavController,destination: String,onLoad: (suspend () -> Unit)? = null) {
    val ani_Duration: Int = 3000
    LaunchedEffect(Unit) {

        val onLoadTask = async {
            onLoad?.invoke()
        }

        val animationTask = async {
            delay(ani_Duration.toLong())
        }
        onLoadTask.await()
        animationTask.await()
        navController.navigate(destination)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ImageFromAssets(
            imgDir = "app/BG_${getImgDirSuffixByTheme()}.png",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageFromAssets(
                imgDir = "app/Logo.png",
                modifier = Modifier.fillMaxSize()
                                    .scale(animate_ScaleZoom(0.5.toFloat(),1.0.toFloat(),ani_Duration)))
        }
    }
}
