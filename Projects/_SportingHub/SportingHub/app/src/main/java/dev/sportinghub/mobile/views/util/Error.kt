package dev.sportinghub.mobile.views.util


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.sportinghub.mobile.server.components.ImageFromAssets
import dev.sportinghub.mobile.server.components.getImgDirSuffixByTheme
import dev.sportinghub.mobile.server.components.getOppositeThemeColor
import dev.sportinghub.mobile.viewmodels.ClientVM

@Composable
fun ErrorScreen(clientVM: ClientVM,navController: NavController,destination: String,onLoad: (suspend () -> Unit)? = null) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ImageFromAssets(
            imgDir = "app/BG_${getImgDirSuffixByTheme()}.png",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(horizontal = 16.dp).align(Alignment.Center)) {
            Text(
                text = "> > Screen < <",
                color = getOppositeThemeColor(),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}
