package com.easy.meet.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.easy.meet.R
import com.easy.meet.navigation.EasyMeetScreens
import com.easy.meet.navigation.bottomNavigation.BottomNavigations
import com.easy.meet.navigation.bottomNavigation.bottomNavigationItems

@ExperimentalComposeUiApi
@Composable
fun HomeScreen(nvController: NavController) {

    val navController = rememberNavController()

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier
                        .height(60.dp)
                        .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)),
                    cutoutShape = CircleShape,
                    elevation = 20.dp,
                    backgroundColor = Color.White
                ) {
                    BottomNav(navController)
                }
            },
            floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = true,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        nvController.navigate(EasyMeetScreens.CreateEventScreen.name)
                    },
                    shape = CircleShape,
                    contentColor = Color.White
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.image)
                    )
                }
            },
            backgroundColor = colorResource(id = R.color.smoky_white)
        ) {
            BottomNavigations(navController, nvController)
        }
    }
}

@Composable
fun BottomNav(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination

    BottomNavigation(
        modifier = Modifier
            .height(100.dp),
        backgroundColor = Color.White,
        elevation = 10.dp
    ) {


        bottomNavigationItems.forEach { screen ->

            val selected = currentRoute?.hierarchy?.any { it.route == screen.route } == true

            BottomNavigationItem(
                icon = {
                    screen.icon?.let {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = "",
                            modifier = Modifier.size(35.dp),
                            tint = if (selected) colorResource(id = R.color.dark_green) else Color.Gray.copy(
                                alpha = 0.4f
                            )
                        )
                    }
                },
                label = {
                    screen.title?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.caption,
                            color = if (selected) colorResource(id = R.color.dark_green) else Color.Gray.copy(
                                alpha = 0.4f
                            )
                        )
                    }
                },
                selected = selected,

                selectedContentColor = Color(R.color.dark_green),
                unselectedContentColor = Color.White.copy(alpha = 0.4f),
                onClick = {
                    screen.route?.let { it1 ->
                        navController.navigate(it1) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }

}
