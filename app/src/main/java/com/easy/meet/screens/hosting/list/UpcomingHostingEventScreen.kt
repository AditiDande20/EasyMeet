package com.easy.meet.screens.hosting.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.easy.meet.R
import com.easy.meet.components.ListLayout
import com.easy.meet.components.ShowTitle
import com.easy.meet.navigation.EasyMeetScreens

@ExperimentalComposeUiApi
@Composable
fun UpcomingHostingEventScreen(navController: NavController,nvController: NavController = NavController(LocalContext.current)){
    Scaffold(topBar = {
        ShowTitle(text = stringResource(R.string.hostingEvent),
            stringResource(R.string.hostingEventDesc)
        )
    }) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            repeat(10){
                ListLayout(eventTitle = "Club house Meeting",
                    eventDate = "31 Oct 2022",
                    eventPlace = "Mumbai",
                    eventStatus = "Unconfirmed",
                    dateCreated = "10 min ago"
                ){
                    nvController.navigate(EasyMeetScreens.DetailEventScreen.name)
                }
            }
        }
    }

}