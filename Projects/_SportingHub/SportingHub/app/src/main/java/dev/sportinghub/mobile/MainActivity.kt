package dev.sportinghub.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.sportinghub.mobile.server.components.InitApp
import dev.sportinghub.mobile.ui.theme.SportingHubTheme
import dev.sportinghub.mobile.viewmodels.ClientVM

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val clientVM: ClientVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SportingHubTheme {
                InitApp(clientVM)
            }
        }
    }
}
