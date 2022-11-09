package com.easy.meet.navigation.bottomNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationScreens(
    val route: String?,
    val title: String?,
    val icon: ImageVector?
) {
    object Hosting : BottomNavigationScreens("hosting", "Hosting", Icons.Default.List)
    object Attending :
        BottomNavigationScreens("attending", "Attending", Icons.Default.Notifications)

    object Create : BottomNavigationScreens("create", null, null)
    object Detail : BottomNavigationScreens("detail", null, null)

}
