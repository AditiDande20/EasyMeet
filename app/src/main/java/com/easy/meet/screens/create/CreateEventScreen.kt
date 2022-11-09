package com.easy.meet.screens.create

import android.os.Build
import android.util.Log
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController
import com.easy.meet.R
import com.easy.meet.components.*
import com.easy.meet.models.Event
import com.easy.meet.screens.create.viewmodel.EventViewModel
import com.easy.meet.utils.Constant
import com.easy.meet.utils.Utils
import com.squaredem.composecalendar.ComposeCalendar
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateEventScreen(navController: NavController, eventViewModel: EventViewModel) {
    Surface {
        Scaffold(topBar = {
            ShowTopBar(
                modifier = Modifier.height(70.dp),
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ShowCreatePage(eventViewModel: EventViewModel) {

    val eventName = remember { mutableStateOf("") }
    val eventDescription = remember { mutableStateOf("") }
    val eventPlace = remember { mutableStateOf("") }
    val flag = remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(2.dp)
            .verticalScroll(state = scrollState)
    ) {
        ShowTitle(
            title = stringResource(id = R.string.eventName),
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)
                .fillMaxWidth(),
            color = Color.Black
        )
        InputField(
            value = eventName,
            placeholder = stringResource(id = R.string.eventName),
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)
                .fillMaxWidth(),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
        )

        ShowTitle(
            title = stringResource(id = R.string.eventDescription),
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)
                .fillMaxWidth(),
            color = Color.Black
        )

        InputField(
            value = eventDescription,
            placeholder = stringResource(id = R.string.eventDescription),
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)
                .fillMaxWidth(),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
        )

        ShowTitle(
            title = stringResource(id = R.string.eventPlace),
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)
                .fillMaxWidth(),
            color = Color.Black
        )

        InputField(
            value = eventPlace,
            placeholder = stringResource(id = R.string.eventPlace),
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)
                .fillMaxWidth(),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
        )

        ShowTitle(
            title = stringResource(id = R.string.addDate),
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)
                .fillMaxWidth(),
            color = Color.Black
        )

        //DatePickerView()

        DatePickerCompose{
            Log.e("Aditi====>", "date  :: $it")
        }

        BorderButton(
            text = stringResource(id = R.string.createEvent), modifier = Modifier
                .padding(5.dp)
                .padding(5.dp)
                .wrapContentWidth()
                .align(CenterHorizontally)
        ) {
            flag.value = true
            saveEvent(eventName, eventDescription, eventPlace, eventViewModel)
        }

        if (flag.value) {
            GenerateEventLink(eventViewModel)
        }

    }
}


private fun saveEvent(
    eventName: MutableState<String>,
    eventDescription: MutableState<String>,
    eventPlace: MutableState<String>,
    eventViewModel: EventViewModel
) {
    val event = Event(
        title = eventName.value,
        description = eventDescription.value,
        place = eventPlace.value,
        status = Constant.UNCONFIRMED,
        link = "",
        final_date = "",
        created_at = Utils.getCurrentDateTime(),
        event_dates = emptyList()
    )

    eventViewModel.insertEvent(event)
}

@Composable
fun GenerateEventLink(eventViewModel: EventViewModel) {
    val id by eventViewModel.state.collectAsState()

    val image =
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9RvF3ix5DRfZmehI-Z4ZabBDJg1xt6eel8w&usqp=CAU"

    eventViewModel.generateSharingLink(
        deepLink = "${Constant.DYNAMIC_LINK_DOMAIN}/?event/${id}".toUri(),
        previewImageLink = image.toUri()
    ) { generatedLink ->
        // Use this generated Link to share via Intent
        Log.e("Aditi === >", "generatedLink :: $generatedLink")
    }
}



