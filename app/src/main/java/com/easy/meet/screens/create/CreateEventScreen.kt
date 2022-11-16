package com.easy.meet.screens.create

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import com.easy.meet.R
import com.easy.meet.components.*
import com.easy.meet.models.Event
import com.easy.meet.screens.create.viewmodel.EventViewModel
import com.easy.meet.ui.theme.QuickSand
import com.easy.meet.utils.Constant
import com.easy.meet.utils.Utils
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun CreateEventScreen(navController: NavController, eventViewModel: EventViewModel) {
    Surface {
        Scaffold(topBar = {
            ShowTopBar(
                modifier = Modifier.height(50.dp),
                text = stringResource(id = R.string.createEvent),
                icon = Icons.Default.ArrowBack,
                elevation = 10.dp,
                color = colorResource(id = R.color.white)
            ) {
                navController.popBackStack()
            }
        }) {
            ShowCreatePage(eventViewModel)
        }
    }
}

@Composable
fun ShowCreatePage(eventViewModel: EventViewModel) {

    val eventName = remember { mutableStateOf("") }
    val eventDescription = remember { mutableStateOf("") }
    val eventPlace = remember { mutableStateOf("") }
    val showDateDialog = remember { mutableStateOf(false) }
    val showTimeDialog = remember { mutableStateOf(false) }
    val eventDatesList = remember { mutableStateListOf("") }
    val eventDates = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(2.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ShowTitle(
            title = stringResource(id = R.string.eventName),
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp, bottom = 2.dp, end = 20.dp)
                .fillMaxWidth(),
            color = Color.Black
        )

        ShowInputField(
            value = eventName,
            placeholder = stringResource(id = R.string.eventNamePlaceholder),
            modifier = Modifier
                .padding(start = 10.dp, top = 5.dp, bottom = 2.dp, end = 10.dp)
                .fillMaxWidth(),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
        )

        ShowTitle(
            title = stringResource(id = R.string.eventDescription),
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp, bottom = 2.dp, end = 20.dp)
                .fillMaxWidth(),
            color = Color.Black
        )

        ShowInputField(
            value = eventDescription,
            placeholder = stringResource(id = R.string.eventDescriptionPlaceholder),
            modifier = Modifier
                .padding(start = 10.dp, top = 5.dp, bottom = 2.dp, end = 10.dp)
                .fillMaxWidth(),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
        )

        ShowTitle(
            title = stringResource(id = R.string.eventPlace),
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp, bottom = 2.dp, end = 20.dp)
                .fillMaxWidth(),
            color = Color.Black
        )

        ShowInputField(
            value = eventPlace,
            placeholder = stringResource(id = R.string.eventPlacePlaceholder),
            modifier = Modifier
                .padding(start = 10.dp, top = 5.dp, bottom = 2.dp, end = 10.dp)
                .fillMaxWidth(),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
        )

        Button(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 10.dp)
                .align(CenterHorizontally),
            onClick = {
                showDateDialog.value = true
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 5.dp,
                pressedElevation = 8.dp,
                disabledElevation = 0.dp
            )
        ) {
            Image(
                imageVector = (Icons.Default.DateRange),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = stringResource(id = R.string.addDate), fontFamily = QuickSand)
        }


        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 2.dp, bottom = 2.dp, end = 10.dp),
            maxLines = 2,
            text = if (eventDatesList.isEmpty()) stringResource(R.string.expectedDatesDescription) else stringResource(
                id = R.string.deleteDatesDescription
            ),
            fontFamily = QuickSand,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )

        if (showDateDialog.value) {
            DatePickerCompose(fun() {
                showDateDialog.value = false
                showTimeDialog.value = true
            }) {
                eventDates.value = it
            }
        }


        if (showTimeDialog.value) {
            TimePickerView(fun() {
                showTimeDialog.value = false
            }) {
                eventDatesList.add("${eventDates.value} $it")
            }
        }

        if (eventDatesList.isNotEmpty()) {
            eventDatesList.removeIf { it == "" }
            val eventDatesChipsList = eventDatesList.distinct().toMutableList()
            ShowChipGroup(dateChipList = eventDatesChipsList){
                eventDatesList.remove(it)
            }

        }

        BorderButton(
            text = stringResource(id = R.string.createEvent), modifier = Modifier
                .padding(5.dp)
                .padding(5.dp)
                .wrapContentWidth()
                .align(CenterHorizontally)
        ) {
            val eventId =
                FirebaseFirestore.getInstance().collection(Constant.EVENT_TABLE).document().id
            saveEvent(
                eventDatesList,
                eventId,
                eventName,
                eventDescription,
                eventPlace,
                eventViewModel
            )
        }
    }
}


private fun saveEvent(
    eventDatesList: MutableList<String>,
    eventId: String,
    eventName: MutableState<String>,
    eventDescription: MutableState<String>,
    eventPlace: MutableState<String>,
    eventViewModel: EventViewModel
) {
    val link = generateEventLink(eventViewModel, eventId)
    if (link.isNotEmpty() && link.isNotBlank()) {
        val event = Event(
            id = eventId,
            title = eventName.value,
            description = eventDescription.value,
            place = eventPlace.value,
            status = Constant.UNCONFIRMED,
            link = link,
            final_date = "",
            created_at = Utils.getCurrentDateTime(),
            event_dates = eventDatesList
        )

        eventViewModel.insertEvent(event)
    } else {
        // link not created ...show message
    }

}

fun generateEventLink(eventViewModel: EventViewModel, id: String): String {
    var link = ""
    val image =
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9RvF3ix5DRfZmehI-Z4ZabBDJg1xt6eel8w&usqp=CAU"

    eventViewModel.generateSharingLink(
        deepLink = "${Constant.DYNAMIC_LINK_DOMAIN}/?event/${id}".toUri(),
        previewImageLink = image.toUri()
    ) { generatedLink ->
        // Use this generated Link to share via Intent
        link = generatedLink
        Log.e("Aditi === >", "generatedLink :: $generatedLink")
    }
    return link
}



