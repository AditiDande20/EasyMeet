package com.easy.meet

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.easy.meet.navigation.EasyMeetNavigation
import com.easy.meet.ui.theme.EasyMeetTheme
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EasyMeetApp()
            getDynamicLinks()
        }
    }

    private fun getDynamicLinks() {
        FirebaseDynamicLinks.getInstance()
            .getDynamicLink(intent)
            .addOnSuccessListener(this@MainActivity) { pendingDynamicLinkData ->

                Log.e("Aditi===>", "pendingDynamicLinkData from dynamic link::${pendingDynamicLinkData}")

                var deepLink: Uri? = null

                if (pendingDynamicLinkData != null) {
                    deepLink = pendingDynamicLinkData.link
                }

                deepLink?.let { uri ->
                    val path = uri.toString().substring(deepLink.toString().lastIndexOf("/") + 1)


                    when {
                        uri.toString().contains("event") -> {
                            val eventID = path.toLong()
                            Log.e("Aditi===>", "event ID from dynamic link::$eventID")

                        }
                    }
                }
            }.addOnFailureListener {
                Log.e("Aditi", "handleIncomingDeepLinks: ${it.message}")
            }
    }
}

@ExperimentalComposeUiApi
@Composable
fun EasyMeetApp() {
    EasyMeetTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            EasyMeetNavigation()
        }
    }
}