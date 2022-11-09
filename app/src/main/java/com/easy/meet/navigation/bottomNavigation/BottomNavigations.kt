package com.easy.meet.navigation.bottomNavigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.easy.meet.screens.attending.UpcomingAttendingEventsScreen
import com.easy.meet.screens.create.CreateEventScreen
import com.easy.meet.screens.create.viewmodel.EventViewModel
import com.easy.meet.screens.hosting.detail.EventDetailScreen
import com.easy.meet.screens.hosting.list.UpcomingHostingEventScreen

@ExperimentalComposeUiApi
@Composable
fun BottomNavigations(navController: NavHostController, nvController: NavController) {

    BottomNavigationScreens.Hosting.route?.let {
        NavHost(navController, startDestination = it) {

            composable(BottomNavigationScreens.Hosting.route) {
                UpcomingHostingEventScreen(navController = navController, nvController)
            }
            BottomNavigationScreens.Attending.route?.let { it1 ->
                composable(it1) {
                    UpcomingAttendingEventsScreen(navController = navController)
                }
            }

            BottomNavigationScreens.Create.route?.let { it1 ->
                composable(it1) {
                    val eventViewModel = hiltViewModel<EventViewModel>()
                    CreateEventScreen(navController, eventViewModel)
                }
            }
            BottomNavigationScreens.Detail.route?.let { it1 ->
                composable(it1) {
                    EventDetailScreen(nvController)
                }
            }
        }
    }
}