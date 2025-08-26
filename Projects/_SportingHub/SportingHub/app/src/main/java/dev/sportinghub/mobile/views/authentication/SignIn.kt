package dev.sportinghub.mobile.views.authentication

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.sportinghub.mobile.server.components.ImageFromAssets
import dev.sportinghub.mobile.server.components.getImgDirSuffixByTheme
import dev.sportinghub.mobile.server.components.getOppositeThemeColor
import dev.sportinghub.mobile.server.components.getThemeColor
import dev.sportinghub.mobile.viewmodels.ClientVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(clientVM: ClientVM, navController: NavController) {
    var userField by remember { mutableStateOf("") }
    var passwordField by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ImageFromAssets(
            imgDir = "app/BG_${getImgDirSuffixByTheme()}.png",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize()
                               .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ImageFromAssets(
                imgDir = "app/Logo_Icon.png",
                modifier = Modifier.scale(1.5f)
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Welcome to The Hub!",
                color = getOppositeThemeColor(),
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = "User",
                color = getOppositeThemeColor(),
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                ),
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp)
                                   .align(Alignment.Start)
            )

            TextField(
                value = userField,
                onValueChange = { userField = it },
                singleLine = true,
                placeholder = { Text("email or nickname") },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    containerColor = Color.White,
                    cursorColor = Color.Black,
                    placeholderColor = Color.LightGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .border(width = 2.dp,color = Color.Black,shape = RoundedCornerShape(18.dp))
                    .clip(RoundedCornerShape(18.dp))
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Password",
                color = getOppositeThemeColor(),
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                ),
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp)
                                   .align(Alignment.Start)
            )

            TextField(
                value = passwordField,
                onValueChange = { passwordField = it },
                singleLine = true,
                placeholder = { Text("password") },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    containerColor = Color.White,
                    cursorColor = Color.Black,
                    placeholderColor = Color.LightGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .border(width = 2.dp,color = Color.Black,shape = RoundedCornerShape(18.dp))
                    .clip(RoundedCornerShape(18.dp))
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = getThemeColor(),
                    contentColor = getOppositeThemeColor()
                ),
                onClick = {
                    CoroutineScope(Dispatchers.Main).launch {
                        val success = clientVM.signIn(
                            email = userField,
                            nickname = userField,
                            password = passwordField
                        )
                        if (success) {
                            val destination: String = when (clientVM.getClientType().toString().uppercase()) {
                                "PERSONAL" -> "Home"
                                "BRAND" -> "OrdersList"
                                "ADMIN" -> "ReportsList"
                                else -> "Error"
                            }
                            navController.navigate("Load/$destination")
                        } else {
                            Toast.makeText(context, "Invalid Credentials", Toast.LENGTH_LONG).show()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.65f)
                    .height(60.dp)
                    .border(2.dp, Color.Black, shape = RoundedCornerShape(24.dp))
            ) {
                Text(
                    text = "Sign in",
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.SansSerif
                    ),
                )
            }
        }
    }
}
