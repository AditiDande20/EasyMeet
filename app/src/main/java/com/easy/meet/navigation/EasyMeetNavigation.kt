package com.easy.meet.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.easy.meet.screens.addAttendance.AddAttendanceScreen
import com.easy.meet.screens.attending.UpcomingAttendingEventsScreen
import com.easy.meet.screens.create.CreateEventScreen
import com.easy.meet.screens.home.HomeScreen
import com.easy.meet.screens.hosting.detail.EventDetailScreen
import com.easy.meet.screens.hosting.list.UpcomingHostingEventScreen
import com.easy.meet.screens.authentication.LoginScreen
import com.easy.meet.screens.authentication.viewmodel.AuthenticationViewModel
import com.easy.meet.screens.create.viewmodel.EventViewModel
import com.easy.meet.screens.splash.SplashScreen

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalComposeUiApi
@Composable
fun EasyMeetNavigation(isBottomNavigation: Boolean? = false) {

    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = if (isBottomNavigation == true) {
            EasyMeetScreens.UpcomingHostingEventsScreen.name
        } else {
            EasyMeetScreens.SplashScreen.name
        }
    ) {
        composable(EasyMeetScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        composable(EasyMeetScreens.LoginScreen.name) {
            val authenticationViewModel = hiltViewModel<AuthenticationViewModel>()
            LoginScreen(navController = navController, authenticationViewModel)
        }
        composable(EasyMeetScreens.HomeScreen.name) {
            HomeScreen(navController)
        }
        composable(EasyMeetScreens.UpcomingHostingEventsScreen.name) {
            UpcomingHostingEventScreen(navController = navController)
        }
        composable(EasyMeetScreens.UpcomingAttendingEventsScreen.name) {
            UpcomingAttendingEventsScreen(navController = navController)
        }
        composable(EasyMeetScreens.CreateEventScreen.name) {
            val eventViewModel = hiltViewModel<EventViewModel>()
            CreateEventScreen(navController,eventViewModel)
        }
        composable(EasyMeetScreens.DetailEventScreen.name) {
            EventDetailScreen(navController)
        }
        composable(EasyMeetScreens.AddAttendanceScreen.name) {
            AddAttendanceScreen(navController)
        }
    }
}