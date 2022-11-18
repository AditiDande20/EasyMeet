package com.easy.meet.screens.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.easy.meet.R
import com.easy.meet.components.ShowAppImage
import com.easy.meet.navigation.EasyMeetScreens
import com.easy.meet.ui.theme.ColorPrimary
import com.easy.meet.ui.theme.ColorPrimaryDark
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Preview
@Composable
fun SplashScreen(navController: NavController = NavController(LocalContext.current)) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = ColorPrimary
    ) {

        LaunchedEffect(Unit) {
            delay(2000L)
            if (FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()) {
                navController.navigate(EasyMeetScreens.LoginScreen.name)

            } else {
                navController.navigate(EasyMeetScreens.HomeScreen.name)
            }
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShowAppImage(
                image = R.drawable.app_image,
                modifier = Modifier.padding(5.dp),
                tint = Color.White
            )
        }

    }
}