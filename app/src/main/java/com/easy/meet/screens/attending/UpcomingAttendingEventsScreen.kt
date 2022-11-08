package com.easy.meet.screens.attending

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.easy.meet.R
import com.easy.meet.components.ListLayout
import com.easy.meet.components.ShowTitle

@ExperimentalComposeUiApi
@Composable
fun UpcomingAttendingEventsScreen(navController: NavController){
    Column {
        ShowTitle(text = stringResource(R.string.attendingEvents),
                    stringResource(R.string.attendingEventDesc)
                )
    }
}