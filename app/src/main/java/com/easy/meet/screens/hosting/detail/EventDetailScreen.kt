package com.easy.meet.screens.hosting.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.easy.meet.R
import com.easy.meet.components.BorderButton
import com.easy.meet.components.ShowButtonIcons
import com.easy.meet.components.ShowImageWithText
import com.easy.meet.components.ShowTopBar
import com.easy.meet.navigation.EasyMeetScreens

@Composable
fun EventDetailScreen(navController: NavController) {
    Scaffold(topBar = {
        ShowTopBar(
            modifier = Modifier.height(70.dp),
            text = "Event Details",
            icon = Icons.Default.ArrowBack,
            elevation = 10.dp,
            color = colorResource(id = R.color.white)
        ) {
            navController.popBackStack()
        }
    }) {
        ShowEventDetails(navController)
    }

}

@Composable
fun ShowEventDetails(navController: NavController) {
    Column(modifier = Modifier
        .padding(10.dp)
        .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
        ) {
        Text(text = "Club House Meeting",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(2.dp)
                .padding(5.dp),
            maxLines = 1
        )
        Text(text = "A template is a form, mold or pattern used as a guide to make something. Here are some examples of templates: Website design. Creating a document. Knitting a sweater.",
            color = Color.Gray,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(2.dp)
                .padding(10.dp),
            maxLines = 3
        )
        Spacer(modifier = Modifier.height(10.dp))

        ShowImageWithText(image = Icons.Default.LocationOn, text = "Mumbai")

        Spacer(modifier = Modifier.height(10.dp))

        ShowImageWithText(image = Icons.Default.DateRange, text = "01 Nov 22")

        Spacer(modifier = Modifier.height(10.dp))

        ShowImageWithText(image = Icons.Default.AddChart, text = "Confirmed")

        Spacer(modifier = Modifier.height(10.dp))

        BorderButton(
            text = "Add Attendance", modifier = Modifier
                .padding(5.dp)
                .padding(5.dp)
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            navController.navigate(EasyMeetScreens.AddAttendanceScreen.name)
        }

        Spacer(modifier = Modifier.height(10.dp))

        EventAttendingChartHeader()

        repeat(5){
            EventAttendingChart()
        }
    }
}

@Composable
fun EventAttendingChartHeader() {

    Row(modifier = Modifier.padding(2.dp)
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically) {

        Text(text = "Expected Dates", fontWeight = FontWeight.Bold, color = Color.Black)

        Row(modifier = Modifier.fillMaxWidth(0.8f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ShowButtonIcons(image = Icons.Default.Check)
            ShowButtonIcons(image = Icons.Default.Help)
            ShowButtonIcons(image = Icons.Default.Close)
        }
    }

    Divider(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp))
}

@Composable
fun EventAttendingChart() {

    Row(modifier = Modifier.padding(2.dp)
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {

        Text(text = "01 Nov 22 ( Thu )")

        Row(modifier = Modifier
            .fillMaxWidth(0.78f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(text = "10")
            Text(text = "2")
            Text(text = "500")
        }

    }



    Divider(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp))
}
